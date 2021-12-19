package com.github.steleal.library.view;

import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

@Component
public class ConsoleHelper implements UiHelper {
    private final PrintStream out;
    private final Scanner scanner;

    public ConsoleHelper() {
        this.out = System.out;
        this.scanner = new Scanner(new InputStreamReader(System.in));
    }

    @Override
    public void print(String message) {
        out.println(message);
    }

    @Override
    public String askInput(String message) {
        if (message != null && !message.isBlank()) {
            print(message);
        }
        String inputLine = scanner.nextLine();
        out.println();
        return inputLine;
    }

    @Override
    public long askLongNumber(String message) {
        String rawNumber = askInput(message);
        try {
            return Long.parseLong(rawNumber);
        } catch (NumberFormatException e) {
            throw new RuntimeException(String.format("String %s is not a number", rawNumber));
        }
    }
}
