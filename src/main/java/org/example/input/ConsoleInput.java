package org.example.input;

import java.util.Scanner;

public class ConsoleInput implements Input{

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public int getInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(getString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("You must enter a number");
            }
        }
    }

    @Override
    public long getLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(getString(prompt));
            } catch (NumberFormatException e) {
                System.out.println("You must enter a number");
            }
        }
    }
}
