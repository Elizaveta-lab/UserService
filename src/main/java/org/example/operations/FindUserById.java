package org.example.operations;

import org.example.input.Input;
import org.example.model.User;
import org.example.output.Output;
import org.example.repository.UserRepository;

public class FindUserById implements Operation{
    @Override
    public String name() {
        return "Find user by id";
    }

    @Override
    public boolean execute(Input input, UserRepository repository, Output output) {
        output.println("\nFind user by id");
        Long id = input.getLong("Enter id: ");
        User user = repository.findUserById(id);
        if (user != null) {
            output.println("User found: " + user);
        } else {
            output.println("User " + id + " not found");
        }
        return true;
    }
}
