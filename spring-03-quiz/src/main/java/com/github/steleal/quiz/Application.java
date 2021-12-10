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
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final QuestionService questionService;
    private final RegistrationController registrationController;
    private final QuizController quizController;
    private final ShowResultController showResultController;
    private final TestController testController;

    @Override
    public void run(String... args) {
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
