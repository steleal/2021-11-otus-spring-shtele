package com.github.steleal.quiz.service.impl;

import com.github.steleal.quiz.dao.QuestionDao;
import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.readAllQuestions();
    }
}
