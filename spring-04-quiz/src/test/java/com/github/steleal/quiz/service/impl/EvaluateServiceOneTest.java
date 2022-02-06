package com.github.steleal.quiz.service.impl;

import com.github.steleal.quiz.domain.Answer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EvaluateServiceOneTest {
    private final List<Answer> answers = Arrays.asList(
            new Answer(false, 0, "black"),
            new Answer(true, 5, "red"),
            new Answer(false, 0, "white"),
            new Answer(false, 0, "gray"));
    private final String rightAnswer = "2";
    private final String[] wrongAnswers = new String[]{
            "1,3,4",
            "",
            "-1",
            "0",
            "1",
            "5",
            "1,2",
            "2,2"
    };

    private final int rightAnswerScore = 5;
    private final int wrongAnswerScore = 0;

    private final EvaluateServiceOne evaluateService = new EvaluateServiceOne();

    @Test
    public void testEvaluate() {
        assertThat(evaluateService.evaluate(answers, rightAnswer), is(rightAnswerScore));
        for (String wrongAnswer : wrongAnswers) {
            assertThat(evaluateService.evaluate(answers, wrongAnswer), is(wrongAnswerScore));
        }
    }

}