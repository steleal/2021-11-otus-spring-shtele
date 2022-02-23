package com.github.steleal.library.controller.impl;

import com.github.steleal.library.controller.AuthorController;
import com.github.steleal.library.service.AuthorService;
import com.github.steleal.library.service.MessageService;
import com.github.steleal.library.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {
    private final UiHelper ui;
    private final MessageService messageService;
    private final AuthorService authorService;

    @Override
    public void printAllAuthors() {
        ui.print(messageService.getMessage("author.list.caption"));
        authorService.getAll().stream()
                .map(a -> a.getId() + "\t" + a.getFullName())
                .forEach(ui::print);
    }
}
