package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.HibernateConfig;
import org.example.input.ConsoleInput;
import org.example.input.Input;
import org.example.operations.*;
import org.example.output.ConsoleOutput;
import org.example.output.Output;
import org.example.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private final Output output;

    public Main(Output output) {
        this.output = output;
    }

    private void showMenu(List<Operation> operations) {
        output.println("\nUser management menu");
        for (int i = 0; i < operations.size(); i++) {
            output.println(i + ". " + operations.get(i).name());
        }
    }

    public void init(Input input, UserRepository repository, List<Operation> operations) {
        boolean run = true;
        while (run) {
            showMenu(operations);
            int select = input.getInt("Select option: ");
            if (select < 0 || select >= operations.size()) {
                output.println("Incorrect number, please select from 0 to " + (operations.size() - 1));
                continue;
            }
            Operation operation = operations.get(select);
            run = operation.execute(input, repository, output);
        }
    }

    static void main() {
        try {
            LOGGER.info("Application started");
            Input input =  new ConsoleInput();
            Output output = new ConsoleOutput();
            UserRepository repository = new UserRepository();
            List<Operation> operations = Arrays.asList(
                new CreateUser(),
                new UpdateUser(),
                new DeleteUser(),
                new FindUserById(),
                new ShowAllUsers(),
                new Exit()
            );
            new Main(output).init(input,repository,operations);
        }catch (Exception e){
            LOGGER.error("Application error", e);
            System.err.println("Error: " + e.getMessage());
        }finally {
            HibernateConfig.shutdown();
            LOGGER.info("Application stopped");
        }
    }
}
