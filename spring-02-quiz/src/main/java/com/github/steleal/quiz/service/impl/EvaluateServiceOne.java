package com.github.steleal.quiz.service.impl;

import com.github.steleal.quiz.domain.Answer;
import com.github.steleal.quiz.domain.QuestionType;
import com.github.steleal.quiz.service.EvaluateService;
import com.github.steleal.quiz.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluateServiceOne implements EvaluateService {
    private final QuestionType type = QuestionType.ONE;

    @Override
    public int evaluate(List<Answer> answers, String studentAnswer) {
        int chosen = getChosenVariant(studentAnswer);

        return chosen < 0 || chosen >= answers.size()
                ? 0
                : answers.get(chosen).getScore();
    }

    private int getChosenVariant(String studentAnswer) {
        return studentAnswer == null || !StringUtils.isIntNumber(studentAnswer)
                ? -1
                : Integer.parseInt(studentAnswer) - 1;
    }
    @Override
    public QuestionType getType() {
        return type;
    }
}
