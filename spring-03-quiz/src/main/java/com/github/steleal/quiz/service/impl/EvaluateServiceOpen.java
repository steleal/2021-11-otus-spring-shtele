package com.github.steleal.quiz.service.impl;

import com.github.steleal.quiz.domain.Answer;
import com.github.steleal.quiz.domain.QuestionType;
import com.github.steleal.quiz.service.EvaluateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceOpen implements EvaluateService {
    private final QuestionType type = QuestionType.OPEN;

    @Override
    public int evaluate(List<Answer> answers, String studentAnswer) {
        studentAnswer = studentAnswer.trim();
        for (Answer answer : answers) {
            if (answer.getText().equalsIgnoreCase(studentAnswer)) {
                return answer.getScore();
            }
        }
        return 0;
    }

    @Override
    public QuestionType getType() {
        return type;
    }
}
