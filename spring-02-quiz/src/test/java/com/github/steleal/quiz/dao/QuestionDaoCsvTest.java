package com.github.steleal.quiz.dao;

import com.github.steleal.quiz.dao.csv.QuestionDaoCsv;
import com.github.steleal.quiz.dao.csv.converter.StringArrToQuestionConverter;
import com.github.steleal.quiz.dao.csv.converter.StringToAnswerConverter;
import com.github.steleal.quiz.domain.Question;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

class QuestionDaoCsvTest {
    private final StringToAnswerConverter answerConverter = new StringToAnswerConverter();
    private final StringArrToQuestionConverter questionConverter = new StringArrToQuestionConverter(answerConverter);
    private final ClassPathResource mockCsv = mock(ClassPathResource.class);
    private final ClassPathResource validCsv = new ClassPathResource("/valid.csv");
    private final ClassPathResource notValidCsv = new ClassPathResource("/not_valid.csv");
    private final QuestionDaoCsv questionDaoCsv = new QuestionDaoCsv(mockCsv, questionConverter);

    @Test
    public void testReadAllQuestions() {
        mockCsvAs(validCsv);
        List<Question> questions = questionDaoCsv.readAllQuestions();
        assertThat(questions, notNullValue(List.class));
        assertThat(questions, hasSize(5));
    }

    @Test
    public void testReadNotValidCsvThrowsRuntimeException() {
        mockCsvAs(notValidCsv);
        assertThrows(RuntimeException.class,
                questionDaoCsv::readAllQuestions,
                "Expected questionService.getAllQuestions() to throw RuntimeException, but it didn't");
    }

    @SneakyThrows
    private void mockCsvAs(ClassPathResource resource) {
        reset(mockCsv);
        when(mockCsv.getInputStream())
                .thenReturn(resource.getInputStream());
    }
}