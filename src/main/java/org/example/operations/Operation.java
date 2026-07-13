package org.example.operations;

import org.example.input.Input;
import org.example.output.Output;
import org.example.repository.UserRepository;

public interface Operation {
    String name();
    boolean execute(Input input, UserRepository repository, Output output);
}
