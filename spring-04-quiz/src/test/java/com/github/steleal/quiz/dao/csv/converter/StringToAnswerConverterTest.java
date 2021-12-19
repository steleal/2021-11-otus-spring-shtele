package com.github.steleal.quiz.dao.csv.converter;

import com.github.steleal.quiz.domain.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class StringToAnswerConverterTest {
    @Autowired
    private StringToAnswerConverter converter;

    @Test
    public void testConvertInNormalCase() {
        String text = "1;10;Test";
        Answer answer = converter.convert(text);
        assertThat(answer, notNullValue(Answer.class));
        assertThat(answer.isNeedToChoose(), is(true));
        assertThat(answer.getScore(), is(10));
        assertThat(answer.getText(), is("Test"));
    }

}