package org.example.operations;

import org.example.input.Input;
import org.example.model.User;
import org.example.output.Output;
import org.example.repository.UserRepository;

public class CreateUser implements Operation{

    @Override
    public String name() {
        return "Create user";
    }

    @Override
    public boolean execute(Input input, UserRepository repository, Output output) {
        output.println("\nCreate new user");
        String name = input.getString("Enter name: ");
        String email = input.getString("Enter email");
        int age = input.getInt("Enter age: ");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(String.valueOf(age));
        Long id = repository.saveUser(user);
        output.println("User created with id: "+id);
        return true;
    }
}
