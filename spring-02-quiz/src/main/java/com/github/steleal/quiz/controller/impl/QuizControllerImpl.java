package com.github.steleal.quiz.controller.impl;

import com.github.steleal.quiz.controller.QuizController;
import com.github.steleal.quiz.controller.converter.QuestionToStringConverter;
import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.domain.QuestionType;
import com.github.steleal.quiz.domain.QuizResult;
import com.github.steleal.quiz.domain.Student;
import com.github.steleal.quiz.service.EvaluateService;
import com.github.steleal.quiz.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class QuizControllerImpl implements QuizController {
    private final UiHelper ui;
    private final Map<QuestionType, EvaluateService> evaluateServiceMap;
    private final QuestionToStringConverter questionConverter;

    @Override
    public QuizResult exam(Student student, List<Question> questions) {
        ui.print("Hello, " + student.getFirstName() + "!");
        int score = 0;
        for (Question question : questions) {
            ui.print(questionConverter.convert(question));
            String answer = ui.askInput(null);
            EvaluateService evalService = evaluateServiceMap.get(question.getType());
            score += evalService.evaluate(question.getAnswers(), answer);
        }
        return new QuizResult(student, score);
    }

}
