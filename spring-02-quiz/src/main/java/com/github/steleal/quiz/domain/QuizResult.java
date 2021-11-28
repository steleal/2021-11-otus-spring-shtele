package com.github.steleal.quiz.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuizResult {
    private final Student student;
    private final int score;
}
