package com.github.steleal.quiz.service;

import com.github.steleal.quiz.dao.QuestionDao;
import com.github.steleal.quiz.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionServiceImplTest {
    private final QuestionDao mockQuestionDao = mock(QuestionDao.class);
    private final QuestionService questionService = new QuestionServiceImpl(mockQuestionDao);

    @BeforeEach
    public void setUp() {
        reset(mockQuestionDao);
    }

    @Test
    public void testInvokeQuestionDaoReadAllQuestions() {
        List<Question> daoQuestions = new ArrayList<>();
        when(mockQuestionDao.readAllQuestions())
                .thenReturn(daoQuestions);

        List<Question> serviceQuestions = questionService.getAllQuestions();

        verify(mockQuestionDao, times(1)).readAllQuestions();
        assertThat(serviceQuestions, sameInstance(daoQuestions));
    }

    @Test()
    public void testThrowExceptionWhenQuestionDaoThrowException() {
        String exceptionMessage = "TestException";
        when(mockQuestionDao.readAllQuestions())
                .thenThrow(new RuntimeException(exceptionMessage));

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                questionService::getAllQuestions,
                "Expected questionService.getAllQuestions() to throw IOException, but it didn't"
        );

        assertThat(thrown.getMessage(), is(exceptionMessage));
    }

}
