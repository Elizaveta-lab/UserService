package org.example.operations;

import org.example.input.Input;
import org.example.output.Output;
import org.example.repository.UserRepository;

public class DeleteUser implements Operation{
    @Override
    public String name() {
        return "Delete user";
    }

    @Override
    public boolean execute(Input input, UserRepository repository, Output output) {
        output.println("\nDelete user");
        Long id = input.getLong("Enter id: ");
        if (repository.deleteUserById(id)){
            output.println("User "+id+" has been deleted");
        }else {
            output.println("Deletion failed");
        }
        return true;
    }
}
