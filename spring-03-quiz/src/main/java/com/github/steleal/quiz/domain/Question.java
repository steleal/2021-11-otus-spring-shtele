package com.github.steleal.quiz.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Question {
    private final QuestionType type;
    private final String text;
    private final List<Answer> answers;
}
