package com.github.steleal.quiz;

import com.github.steleal.quiz.domain.Answer;
import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context
                     = new ClassPathXmlApplicationContext("/resources/spring-context.xml")) {

            context.getBean(QuestionService.class)
                    .getAllQuestions()
                    .forEach(question -> System.out.println(mapToString(question)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // todo убрать во view слой
    private static String mapToString(Question question) {
        StringBuilder builder = new StringBuilder(question.getText());
        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            builder.append("\n\t")
                    .append((i + 1))
                    .append(". ")
                    .append(answers.get(i).getText());
        }
        builder.append("\n");
        return builder.toString();
    }
}
