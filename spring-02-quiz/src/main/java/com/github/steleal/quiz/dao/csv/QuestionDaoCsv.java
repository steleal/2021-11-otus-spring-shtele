package com.github.steleal.quiz.dao.csv;

import com.github.steleal.quiz.dao.QuestionDao;
import com.github.steleal.quiz.domain.Question;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDaoCsv implements QuestionDao {
    private final ClassPathResource questionCsvResource;
    private final Converter<String[], Question> stringToQuestionConverter;

    @Override
    public List<Question> readAllQuestions() {
        try (CSVReader reader = getCsvReader()) {
            List<Question> questions = new ArrayList<>();
            String[] cells;
            while ((cells = reader.readNext()) != null) {
                if (cells.length == 0 || cells[0].isBlank()) {
                    continue;
                }
                questions.add(stringToQuestionConverter.convert(cells));
            }
            return questions;
        } catch (IOException e) {
            throw new RuntimeException("Can not to read " + questionCsvResource.getFilename());
        } catch (CsvValidationException e) {
            throw new RuntimeException(questionCsvResource.getFilename() + "is not CSV file");
        }
    }

    private CSVReader getCsvReader() throws IOException {
        return new CSVReader(new InputStreamReader(questionCsvResource.getInputStream()));
    }
}
