package com.github.steleal.library.controller.impl;

import com.github.steleal.library.controller.AuthorController;
import com.github.steleal.library.controller.BookController;
import com.github.steleal.library.controller.GenreController;
import com.github.steleal.library.domain.Book;
import com.github.steleal.library.service.BookService;
import com.github.steleal.library.service.MessageService;
import com.github.steleal.library.view.UiHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {
    private final Converter<Book, String> bookToString;
    private final UiHelper ui;
    private final MessageService messageService;
    private final BookService bookService;
    private final AuthorController authorController;
    private final GenreController genreController;

    @Override
    public boolean isBooksEmpty() {
        return bookService.count() == 0;
    }

    @Override
    public void updateBook() {
        printAllBooks();
        long bookId = ui.askLongNumber(messageService.getMessage("book.ask.id"));
        bookService.findById(bookId);
        String title = ui.askInput(messageService.getMessage("book.ask.title"));
        authorController.printAllAuthors();
        long authorId = ui.askLongNumber(messageService.getMessage("book.ask.author.id"));
        genreController.printAllGenres();
        long genreId = ui.askLongNumber(messageService.getMessage("book.ask.genre.id"));
        bookService.update(bookId, title, authorId, genreId);
        ui.print(messageService.getMessage("book.update.ok"));
    }

    @Override
    public void deleteBook() {
        printAllBooks();
        long bookId = ui.askLongNumber(messageService.getMessage("book.ask.id"));
        bookService.delete(bookId);
        ui.print(messageService.getMessage("book.delete.ok"));
    }

    @Override
    public void addBook() {
        String title = ui.askInput(messageService.getMessage("book.ask.title"));
        authorController.printAllAuthors();
        long authorId = ui.askLongNumber(messageService.getMessage("book.ask.author.id"));
        genreController.printAllGenres();
        long genreId = ui.askLongNumber(messageService.getMessage("book.ask.genre.id"));
        bookService.create(title, authorId, genreId);
        ui.print(messageService.getMessage("book.add.ok"));
    }

    @Override
    public void printBook() {
        printAllBooks();
        long bookId = ui.askLongNumber(messageService.getMessage("book.ask.id"));
        Book book = bookService.findById(bookId);
        ui.print(bookToString.convert(book));
    }

    @Override
    public void printAllBooks() {
        ui.print(messageService.getMessage("book.list.caption"));
        bookService.getAll().stream()
                .map(bookToString::convert)
                .forEach(ui::print);
    }
}
