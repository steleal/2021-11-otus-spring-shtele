package com.github.steleal.quiz.service;

import com.github.steleal.quiz.dao.QuestionDao;
import com.github.steleal.quiz.domain.Question;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.readAllQuestions();
    }
}
