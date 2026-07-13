package org.example.config;

import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class HibernateExecutor {
    private final SessionFactory sessionFactory;
    private final Logger logger;

    public HibernateExecutor(SessionFactory sessionFactory, Logger logger) {
        this.sessionFactory = sessionFactory;
        this.logger = logger;
    }

    public <T> T executeInSession(Function<Session, T> function,
                                  Function<T, String> successLog,
                                  Supplier<String> notFoundLog) {
        try (Session session = sessionFactory.openSession()) {
            T result = function.apply(session);
            if (result != null) {
                logger.info(successLog.apply(result));
            } else if (notFoundLog != null) {
                logger.info(notFoundLog.get());
            }
            return result;
        } catch (HibernateException e) {
            logger.error("Operation error", e);
            throw new PersistenceException("Data access error", e);
        }
    }

    public <T> T executeInSession(Function<Session, T> function,
                                  Function<T, String> successLog) {
        return executeInSession(function, successLog, null);
    }

    public <T> T executeInTransactionWithResult(Function<Session, T> operation,
                                                String successMessage) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T result = operation.apply(session);
            transaction.commit();
            logger.info(successMessage);
            return result;
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    logger.error("Transaction rollback failed", rollbackEx);
                }
            }
            logger.error("The transaction operation failed", e);
            throw new PersistenceException("The transaction operation failed", e);
        }
    }
    public void executeInTransaction(Consumer<Session> operation,
                                     String successMessage) {
        executeInTransactionWithResult(session -> {
            operation.accept(session);
            return null;
        }, successMessage);
    }
}
