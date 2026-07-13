package org.example.operations;

import org.example.input.Input;
import org.example.model.User;
import org.example.output.Output;
import org.example.repository.UserRepository;

public class UpdateUser implements Operation{
    @Override
    public String name() {
        return "Update user";
    }

    @Override
    public boolean execute(Input input, UserRepository repository, Output output) {
       output.println("\nUpdate user");
       Long id = input.getLong("Enter user id: ");
        User user = repository.findUserById(id);
        if (user==null){
            output.println("User "+id+" not found");
            return true;
        }
        output.println("Current data: "+user);
        String name = input.getString("Type a new name or enter: ");
        if (!name.isEmpty()) {
            user.setName(name);
        }
        String email = input.getString("Type a new email or enter: ");
        if (!email.isEmpty()) {
            user.setEmail(email);
        }
        String age = input.getString("Type a new age or enter: ");
        if (!age.isEmpty()) {
            user.setAge(age);
        }
        if (repository.updateUser(user)) {
            output.println("User updated");
        } else {
            output.println("Update failed");
        }
        return true;
    }
}
