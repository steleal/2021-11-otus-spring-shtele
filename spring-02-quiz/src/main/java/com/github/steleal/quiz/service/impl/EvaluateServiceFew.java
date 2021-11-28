package com.github.steleal.quiz.service.impl;

import com.github.steleal.quiz.domain.Answer;
import com.github.steleal.quiz.domain.QuestionType;
import com.github.steleal.quiz.service.EvaluateService;
import com.github.steleal.quiz.service.impl.converter.StringToAnswerNumSetConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Предложены несколько вариантов ответов, требуется выбрать все верные.
 * В ответе должны быть указаны номера правильных вариантов через запятую.
 * Если выбран не требующий выбора или не выбран требующий выбора - вернуть 0 баллов.
 */
@Service
@RequiredArgsConstructor
public class EvaluateServiceFew implements EvaluateService {
    private final QuestionType type = QuestionType.FEW;
    private final StringToAnswerNumSetConverter converter;

    @Override
    public int evaluate(List<Answer> answers, String studentAnswer) {
        Set<Integer> chosenVariants = converter.convert(studentAnswer);
        int score = 0;
        for (int i = 0; i < answers.size(); i++) {
            Answer answer = answers.get(i);
            boolean isChosen = chosenVariants.contains(i + 1);
            if (answer.isNeedToChoose() != isChosen) {
                return 0;
            }
            score += answer.getScore();
        }

        return score;
    }

    @Override
    public QuestionType getType() {
        return type;
    }
}
