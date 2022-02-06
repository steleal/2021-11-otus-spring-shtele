package com.github.steleal.quiz.dao;

import com.github.steleal.quiz.dao.csv.QuestionDaoCsv;
import com.github.steleal.quiz.domain.Question;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionDaoCsvTest {
    @MockBean
    private ClassPathResource questionCsvResource;
    @Autowired
    private QuestionDaoCsv questionDaoCsv;

    @Test
    public void testReadAllQuestions() {
        mockCsvAs(new ClassPathResource("/valid.csv"));
        List<Question> questions = questionDaoCsv.readAllQuestions();
        assertThat(questions, notNullValue(List.class));
        assertThat(questions, hasSize(5));
    }

    @Test
    public void testReadNotValidCsvThrowsRuntimeException() {
        mockCsvAs(new ClassPathResource("/not_valid.csv"));
        assertThrows(RuntimeException.class,
                questionDaoCsv::readAllQuestions,
                "Expected questionService.getAllQuestions() to throw RuntimeException, but it didn't");
    }

    @SneakyThrows
    private void mockCsvAs(ClassPathResource resource) {
        when(questionCsvResource.getInputStream())
                .thenReturn(resource.getInputStream());
    }

    @Configuration
    @ComponentScan("com.github.steleal.quiz.dao.csv")
    public static class QuestionDaoCsvTestConfiguration {
    }
}