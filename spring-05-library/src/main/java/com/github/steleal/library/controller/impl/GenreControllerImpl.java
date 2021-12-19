package com.github.steleal.library.controller.impl;

import com.github.steleal.library.controller.GenreController;
import com.github.steleal.library.service.GenreService;
import com.github.steleal.library.service.MessageService;
import com.github.steleal.library.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreControllerImpl implements GenreController {
    private final UiHelper ui;
    private final MessageService messageService;
    private final GenreService genreService;

    @Override
    public void printAllGenres() {
        String caption = messageService.getMessage("genre.list.caption");
        ui.print(caption);
        genreService.getAll().stream()
                .map(g -> g.getId() + "\t" + g.getName())
                .forEach(ui::print);
    }
}
