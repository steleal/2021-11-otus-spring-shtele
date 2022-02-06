package com.github.steleal.quiz.service;

import com.github.steleal.quiz.domain.Answer;
import com.github.steleal.quiz.domain.QuestionType;

import java.util.List;

public interface EvaluateService {
    int evaluate(List<Answer> answers, String studentAnswer);
    QuestionType getType();
}
