package com.github.steleal.quiz.controller.impl;

import com.github.steleal.quiz.controller.ShowResultController;
import com.github.steleal.quiz.domain.QuizResult;
import com.github.steleal.quiz.domain.Student;
import com.github.steleal.quiz.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowResultControllerImpl implements ShowResultController {
    private final UiHelper ui;
    private final int passScore;
    private final int maxScore;

    @Override
    public void show(QuizResult result) {
        ui.print(convertToString(result));
    }

    private String convertToString(QuizResult result) {
        String name = convertToString(result.getStudent());
        String passFail = result.getScore() < passScore ? "FAIL" : "PASS";
        return String.format(
                "Student %s %s the Quiz with %s from %s maximum possibles score%n",
                name, passFail, result.getScore(), maxScore);
    }

    private String convertToString(Student student) {
        return student.getFirstName() + " " + student.getLastName();
    }
}
