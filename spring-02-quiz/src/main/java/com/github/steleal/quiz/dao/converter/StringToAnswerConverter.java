package com.github.steleal.quiz.dao.converter;

import com.github.steleal.quiz.domain.Answer;
import org.springframework.core.convert.converter.Converter;

public class StringToAnswerConverter implements Converter<String, Answer> {
    @Override
    public Answer convert(String text) {
        return new Answer(false, text);
    }
}
