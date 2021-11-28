package com.github.steleal.quiz.controller.impl;

import com.github.steleal.quiz.controller.RegistrationController;
import com.github.steleal.quiz.domain.Student;
import com.github.steleal.quiz.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationControllerImpl implements RegistrationController {
    private final UiHelper ui;

    @Override
    public Student register() {
        String firstName = ui.askInput("Please, input your first name");
        String lastName = ui.askInput("Please, input your last name");
        while (firstName.isBlank() || lastName.isBlank()) {
            ui.print("Names have not to be empty!");
            firstName = ui.askInput("Please, input your first name");
            lastName = ui.askInput("Please, input your last name");
        }

        return new Student(firstName, lastName);
    }
}
