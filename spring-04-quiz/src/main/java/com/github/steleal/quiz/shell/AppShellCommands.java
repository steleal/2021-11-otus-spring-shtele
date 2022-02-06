package com.github.steleal.quiz.shell;

import com.github.steleal.quiz.controller.QuizController;
import com.github.steleal.quiz.controller.RegistrationController;
import com.github.steleal.quiz.controller.ShowResultController;
import com.github.steleal.quiz.domain.Question;
import com.github.steleal.quiz.domain.QuizResult;
import com.github.steleal.quiz.domain.Student;
import com.github.steleal.quiz.service.MessageService;
import com.github.steleal.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class AppShellCommands {
    private final QuestionService questionService;
    private final RegistrationController registrationController;
    private final QuizController quizController;
    private final ShowResultController showResultController;
    private final MessageService messageService;

    private List<Question> questions;
    private Student student;
    private QuizResult quizResult;

    @PostConstruct
    public void initialize() {
        questions = questionService.getAllQuestions();
        student = null;
        quizResult = null;
    }

    @ShellMethod(value = "Registration of student", key = {"1", "r", "reg"})
    public void registerStudent() {
        student = registrationController.register();
    }

    @ShellMethod(value = "Exam of student", key = {"2", "e", "exam"})
    @ShellMethodAvailability(value = "isExamStudentCommandAvailable")
    public void examStudent() {
        quizResult = quizController.exam(student, questions);
    }

    @ShellMethod(value = "Print of test result", key = {"3", "p", "print"})
    @ShellMethodAvailability(value = "isPrintQuizResultCommandAvailable")
    public void printQuizResult() {
        showResultController.show(quizResult);
    }

    private Availability isExamStudentCommandAvailable() {
        return Objects.isNull(student) ?
                Availability.unavailable(messageService.getMessage("shell.no.student")) :
                Availability.available();
    }

    private Availability isPrintQuizResultCommandAvailable() {
        return Objects.isNull(quizResult) ?
                Availability.unavailable(messageService.getMessage("shell.no.result")) :
                Availability.available();
    }

}
