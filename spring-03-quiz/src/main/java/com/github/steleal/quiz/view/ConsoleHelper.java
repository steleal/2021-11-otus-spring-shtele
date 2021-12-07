package com.github.steleal.quiz.view;

import org.apache.commons.lang3.StringUtils;
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
        if (!StringUtils.isBlank(message)) {
            print(message);
        }
        String inputLine = scanner.nextLine();
        out.println();
        return inputLine;
    }
}
