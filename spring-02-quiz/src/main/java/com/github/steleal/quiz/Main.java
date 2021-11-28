package com.github.steleal.quiz;

import com.github.steleal.quiz.controller.QuizController;
import com.github.steleal.quiz.controller.RegistrationController;
import com.github.steleal.quiz.controller.ShowResultController;
import com.github.steleal.quiz.controller.TestController;
import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.domain.QuizResult;
import com.github.steleal.quiz.domain.Student;
import com.github.steleal.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ComponentScan
@Configuration
@RequiredArgsConstructor
public class Main {
    private final QuestionService questionService;
    private final RegistrationController registrationController;
    private final QuizController quizController;
    private final ShowResultController showResultController;
    private final TestController testController;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        context.getBean(Main.class).run();
    }

    public void run() {
        testController.hello();
        List<Question> questions = questionService.getAllQuestions();
        while (testController.hasTested()) {
            Student student = registrationController.register();
            QuizResult result = quizController.exam(student, questions);
            showResultController.show(result);
        }
        testController.bye();
    }
}
