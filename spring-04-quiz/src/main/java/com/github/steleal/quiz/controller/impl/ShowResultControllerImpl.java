package com.github.steleal.quiz.controller.impl;

import com.github.steleal.quiz.config.AppProperties;
import com.github.steleal.quiz.controller.ShowResultController;
import com.github.steleal.quiz.domain.QuizResult;
import com.github.steleal.quiz.domain.Student;
import com.github.steleal.quiz.service.MessageService;
import com.github.steleal.quiz.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowResultControllerImpl implements ShowResultController {
    private final UiHelper ui;
    private final AppProperties appProps;
    private final MessageService messageService;


    @Override
    public void show(QuizResult result) {
        ui.print(convertToString(result));
    }

    private String convertToString(QuizResult result) {
        String name = convertToString(result.getStudent());
        String passFail = result.getScore() < appProps.getQuizScorePass()
                ? messageService.getMessage("result.fail")
                : messageService.getMessage("result.pass");
        return messageService.getMessage("result.template",
                name, passFail, String.valueOf(result.getScore()),
                String.valueOf(appProps.getQuizScoreMax()),
                String.valueOf(appProps.getQuizScorePass())
        );
    }

    private String convertToString(Student student) {
        return student.getFirstName() + " " + student.getLastName();
    }
}
