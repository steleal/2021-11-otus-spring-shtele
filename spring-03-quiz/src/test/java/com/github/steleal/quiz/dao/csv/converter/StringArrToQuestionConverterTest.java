package com.github.steleal.quiz.dao.csv.converter;

import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.domain.QuestionType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class StringArrToQuestionConverterTest {
    private final StringArrToQuestionConverter converter = new StringArrToQuestionConverter(new StringToAnswerConverter());

    @Test
    void testConvertInNormalCase() {
        String questionText = "How many colours has a rainbow, type a number of variant";
        String[] parts = new String[]{"ONE",
                questionText,
                "0;0;five",
                "0;0;six",
                "1;2;seven",
                "0;0;eight"};
        Question question = converter.convert(parts);
        assertThat(question, notNullValue(Question.class));
        assertThat(question.getText(), is(questionText));
        assertThat(question.getType(), is(QuestionType.ONE));
        assertThat(question.getAnswers(), notNullValue());
        assertThat(question.getAnswers().size(), is(4));
    }
}