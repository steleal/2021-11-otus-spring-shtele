package com.github.steleal.quiz.dao.csv.converter;

import com.github.steleal.quiz.domain.Answer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class StringToAnswerConverterTest {
    private final StringToAnswerConverter converter = new StringToAnswerConverter();

    @Test
    void testConvertInNormalCase() {
        String text = "1;10;Test";
        Answer answer = converter.convert(text);
        assertThat(answer, notNullValue(Answer.class));
        assertThat(answer.isNeedToChoose(), is(true));
        assertThat(answer.getScore(), is(10));
        assertThat(answer.getText(), is("Test"));
    }

}