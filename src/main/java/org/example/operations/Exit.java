package org.example.operations;

import org.example.input.Input;
import org.example.output.Output;
import org.example.repository.UserRepository;

public class Exit implements Operation{
    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, UserRepository repository, Output output) {
        output.println("Exit application");
        if (input.getString("Are you sure? (y/n): ").equalsIgnoreCase("y")){
            output.println("Application closed");
            return false;
        }
        return true;
    }
}
