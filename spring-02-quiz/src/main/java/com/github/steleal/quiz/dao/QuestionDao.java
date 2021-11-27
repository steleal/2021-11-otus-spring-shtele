package com.github.steleal.quiz.dao;

import com.github.steleal.quiz.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> readAllQuestions();
}
