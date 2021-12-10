package com.github.steleal.quiz.dao.csv.converter;

import com.github.steleal.quiz.domain.Answer;
import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.domain.QuestionType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StringArrToQuestionConverter implements Converter<String[], Question> {
    private final Converter<String, Answer> stringToAnswerConverter;

    @Override
    public Question convert(String[] parts) {
        try {
            QuestionType answersType = QuestionType.valueOf(parts[0]);
            String questionText = parts[1];
            List<Answer> answers = new ArrayList<>();
            for (int i = 2; i < parts.length; i++) {
                answers.add(stringToAnswerConverter.convert(parts[i]));
            }
            return new Question(answersType, questionText, answers);
        } catch (Exception e) {
            throw new RuntimeException("Wrong question file format");
        }
    }

}
