package com.github.steleal.quiz.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Answer {
    private final boolean needToChoose;
    private final String text;
}
