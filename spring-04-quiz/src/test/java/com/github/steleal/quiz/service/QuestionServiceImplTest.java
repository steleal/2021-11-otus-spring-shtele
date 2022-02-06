package com.github.steleal.quiz.service;

import com.github.steleal.quiz.dao.QuestionDao;
import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionServiceImplTest {
    @Autowired
    private QuestionDao mockQuestionDao;
    @Autowired
    private QuestionService questionService;

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

    @Configuration
    static class QuestionServiceImplTestConfiguration {
        @MockBean
        private QuestionDao mockQuestionDao;

        @Bean
        public QuestionDao mockQuestionDao() {
            return mockQuestionDao;
        }

        @Bean
        public QuestionService questionService() {
            return new QuestionServiceImpl(mockQuestionDao);
        }
    }
}
