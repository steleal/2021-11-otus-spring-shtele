package com.github.steleal.quiz.controller.converter;

import com.github.steleal.quiz.domain.Answer;
import com.github.steleal.quiz.domain.Question;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.github.steleal.quiz.domain.QuestionType.OPEN;

@Component
public class QuestionToStringConverter implements Converter<Question, String> {
    @Override
    public String convert(Question question) {
        return question.getType() == OPEN
                ? question.getText()
                : getTextWithAnswers(question);
    }

    private String getTextWithAnswers(Question question) {
        StringBuilder builder = new StringBuilder(question.getText());
        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            builder.append("\n\t")
                    .append((i + 1))
                    .append(". ")
                    .append(answers.get(i).getText());
        }
        return builder.toString();
    }
}
