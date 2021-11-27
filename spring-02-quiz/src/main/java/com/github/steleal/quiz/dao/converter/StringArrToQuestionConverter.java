package com.github.steleal.quiz.dao.converter;

import com.github.steleal.quiz.domain.Answer;
import com.github.steleal.quiz.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class StringArrToQuestionConverter implements Converter<String[], Question> {
    private final Converter<String, Answer> toAnswer;

    @Override
    public Question convert(String[] parts) {
        List<Answer> answers = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            answers.add(toAnswer.convert(parts[i]));
        }
        return new Question(parts[0], answers);
    }
}
