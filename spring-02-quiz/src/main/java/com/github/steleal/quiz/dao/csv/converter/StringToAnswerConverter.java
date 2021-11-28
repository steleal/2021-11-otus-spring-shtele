package com.github.steleal.quiz.dao.csv.converter;

import com.github.steleal.quiz.domain.Answer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToAnswerConverter implements Converter<String, Answer> {
    private final static char DELIM = ';';

    @Override
    public Answer convert(String text) {
        int first = text.indexOf(DELIM);
        int second = text.indexOf(DELIM, first + 1);

        boolean needToChose = "1".equals(text.substring(0, first).trim());
        int score = Integer.parseInt(text.substring(first + 1, second).trim());
        String answerText = text.substring(second + 1);

        return new Answer(needToChose, score, answerText);
    }

}
