package org.example.repository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.HibernateConfig;
import org.example.config.HibernateExecutor;
import org.example.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    private  static  final Logger LOGGER = LogManager.getLogger(UserRepository.class);
    private final HibernateExecutor executor;

    public UserRepository() {
        this.executor = new HibernateExecutor(
                HibernateConfig.getSessionFactory(),
                LOGGER
        );
    }

    public Long saveUser(User user){
        return executor.executeInTransactionWithResult(
                session ->(Long) session.save(user),
                "User saved: "+user
        );
    }

    public boolean updateUser(User user){
        try {
            executor.executeInTransaction(
                    session -> session.update(user),
                    "User updated: "+user
            );
            return true;
        }catch (Exception e){
            LOGGER.error("Error updating user: {}",user,e);
            return false;
        }
    }

    public void deleteUser(User user) {
        executor.executeInTransaction(
                session -> session.delete(user),
                "User deleted: " + user
        );
    }

    public User findUserById(Long id) {
        return executor.executeInSession(
                session -> session.get(User.class, id),
                user -> "User found: " + user,
                () -> "Error search user by id: " + id
        );
    }

    public boolean deleteUserById(Long id) {
        return Optional.ofNullable(findUserById(id))
                .map(user -> {
                    deleteUser(user);
                    return true;
                })
                .orElse(false);
    }

    public List<User> findAllUsers() {
        return executor.executeInSession(
                session -> session.createQuery("FROM User", User.class).list(),
                users -> users.size() + " users found"
        );
    }
}
