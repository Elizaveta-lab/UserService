package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Long saveUser(User user){return  repository.saveUser(user);}
    public boolean updateUser(User user){return repository.updateUser(user);}
    public void deleteUser(User user){repository.deleteUser(user);}
    public User findUserById(Long id){return repository.findUserById(id);}
    public boolean deleteUserById(Long id){return repository.deleteUserById(id);}
    public List<User> findAllUsers(){return repository.findAllUsers();}
}
