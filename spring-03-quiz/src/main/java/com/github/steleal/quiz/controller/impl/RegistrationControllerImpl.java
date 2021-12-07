package com.github.steleal.quiz.controller.impl;

import com.github.steleal.quiz.controller.RegistrationController;
import com.github.steleal.quiz.domain.Student;
import com.github.steleal.quiz.service.MessageService;
import com.github.steleal.quiz.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationControllerImpl implements RegistrationController {
    private final UiHelper ui;
    private final MessageService messageService;

    @Override
    public Student register() {
        String firstName = ui.askInput(messageService.getMessage("registration.question.first.name"));
        String lastName = ui.askInput(messageService.getMessage("registration.question.last.name"));
        while (firstName.isBlank() || lastName.isBlank()) {
            ui.print(messageService.getMessage("registration.empty.name"));
            firstName = ui.askInput(messageService.getMessage("registration.question.first.name"));
            lastName = ui.askInput(messageService.getMessage("registration.question.last.name"));
        }

        return new Student(firstName, lastName);
    }
}
