package org.example.operations;

import org.example.input.Input;
import org.example.model.User;
import org.example.output.Output;
import org.example.repository.UserRepository;

import java.util.List;

public class ShowAllUsers implements Operation{
    @Override
    public String name() {
        return "Show all users";
    }

    @Override
    public boolean execute(Input input, UserRepository repository, Output output) {
        output.println("\nList of users");
        List<User> users = repository.findAllUsers();
        if (users.isEmpty()) {
            output.println("Users not found");
        } else {
            users.forEach(user -> output.println(user.toString()));
        }
        return true;
    }
}
