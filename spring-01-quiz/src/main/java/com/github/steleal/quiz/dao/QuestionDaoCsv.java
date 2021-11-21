package com.github.steleal.quiz.dao;

import com.github.steleal.quiz.domain.Question;
import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class QuestionDaoCsv implements QuestionDao {
    private final ClassPathResource questionCsvResource;
    private final Converter<String[], Question> toQuestion;

    @Override
    @SneakyThrows
    public List<Question> readAllQuestions() {
        try (CSVReader reader = getCsvReader()) {
            List<Question> questions = new ArrayList<>();
            String[] cells;
            while ((cells = reader.readNext()) != null) {
                if (cells.length == 0 || cells[0].isBlank()) {
                    continue;
                }
                questions.add(toQuestion.convert(cells));
            }
            return questions;
        }
    }

    private CSVReader getCsvReader() throws IOException {
        return new CSVReader(new InputStreamReader(questionCsvResource.getInputStream()));
    }
}
