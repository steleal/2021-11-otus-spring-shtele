package com.github.steleal.quiz.controller;

import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.domain.QuizResult;
import com.github.steleal.quiz.domain.Student;

import java.util.List;

public interface QuizController {
    QuizResult exam(Student student, List<Question> questions);
}
