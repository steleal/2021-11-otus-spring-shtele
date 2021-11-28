package com.github.steleal.quiz.controller.impl;

import com.github.steleal.quiz.controller.TestController;
import com.github.steleal.quiz.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestControllerImpl implements TestController {
    private final String helloMessage;
    private final String byeMessage;
    private final UiHelper ui;

    @Override
    public void hello() {
        ui.print(helloMessage);
    }

    @Override
    public boolean hasTested() {
        String answer = ui.askInput("Do you want to try the quiz? Type \"NO\" if not.");
        return !"NO".equalsIgnoreCase(answer);
    }

    @Override
    public void bye() {
        ui.print(byeMessage);
    }
}
