package org.example.input;

public interface Input {
    String getString(String prompt);

    int getInt(String prompt);

    long getLong(String prompt);
}
