package dao;

import org.example.config.HibernateConfig;
import org.example.config.HibernateExecutor;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

private UserRepository repository;


    @BeforeAll
    public void setUp() {
        repository = new UserRepository();
    }

        @Test
        public void testSaveUser() {
            User user = new User();
            user.setName("Eva");
            user.setEmail("eva@gmail.com");
            user.setAge(String.valueOf(22));

            repository.saveUser(user);

            User retrievedUser = repository.findUserById(user.getId());
            assertNotNull(retrievedUser);
            assertEquals(user.getName(), retrievedUser.getName());
            repository.deleteUser(user);
        }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("Anya");
        user.setEmail("anya@gmail.com");
        user.setAge(String.valueOf(24));

        repository.saveUser(user);

        user.setName("Alina");
        repository.updateUser(user);

        User retrievedUser = repository.findUserById(user.getId());
        assertNotNull(retrievedUser);
        assertEquals("Alina", retrievedUser.getName());
        repository.deleteUser(user);
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setName("Jack");
        user.setEmail("jack@gmail.com");
        user.setAge(String.valueOf(26));

        repository.saveUser(user);

        User retrievedUser = repository.findUserById(user.getId());
        assertNotNull(retrievedUser);
        assertEquals(user.getName(), retrievedUser.getName());
        repository.deleteUser(user);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setName("User1");
        user1.setEmail("user1@gmail.com");
        user1.setAge(String.valueOf(27));

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("user2@gmail.com");
        user2.setAge(String.valueOf(25));

        repository.saveUser(user1);
        repository.saveUser(user2);

        List<User> users = repository.findAllUsers();
        assertNotNull(users);
        repository.deleteUser(user1);
        repository.deleteUser(user2);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setName("Hanna");
        user.setEmail("hanna@gmail.com");
        user.setAge(String.valueOf(20));

        repository.saveUser(user);

        repository.deleteUser(user);

        User retrievedUser = repository.findUserById(user.getId());
        assertNull(retrievedUser);
    }
}
