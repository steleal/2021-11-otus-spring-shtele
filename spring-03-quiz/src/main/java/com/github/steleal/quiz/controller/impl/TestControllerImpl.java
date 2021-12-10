package com.github.steleal.quiz.controller.impl;

import com.github.steleal.quiz.controller.TestController;
import com.github.steleal.quiz.service.MessageService;
import com.github.steleal.quiz.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestControllerImpl implements TestController {
    private final MessageService messageService;
    private final UiHelper ui;

    @Override
    public void hello() {
        ui.print(messageService.getMessage("test.hello"));
    }

    @Override
    public boolean hasTested() {
        String answer = ui.askInput(messageService.getMessage("test.question.try"));
        return !messageService.getMessage("test.answer.no").equalsIgnoreCase(answer);
    }

    @Override
    public void bye() {
        ui.print(messageService.getMessage("test.bye"));
    }


}
