package com.github.steleal.library.shell;

import com.github.steleal.library.controller.AuthorController;
import com.github.steleal.library.controller.BookController;
import com.github.steleal.library.controller.GenreController;
import com.github.steleal.library.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final BookController bookController;
    private final AuthorController authorController;
    private final GenreController genreController;
    private final MessageService messageService;

    @ShellMethod(value = "Print list of authors", key = {"1", "a"})
    public void printListOfAuthors() {
        authorController.printAllAuthors();
    }

    @ShellMethod(value = "Print list of genres", key = {"2", "g"})
    public void printListOfGenres() {
        genreController.printAllGenres();
    }

    @ShellMethod(value = "Print list of books", key = {"3", "b"})
    public void printListOfBooks() {
        bookController.printAllBooks();
    }

    @ShellMethod(value = "Add new book", key = {"4", "ba"})
    public void addBook() {
        bookController.addBook();
    }

    @ShellMethod(value = "Print a book", key = {"5", "bp"})
    @ShellMethodAvailability(value = "isBooksNotEmpty")
    public void printBook() {
        bookController.printBook();
    }

    @ShellMethod(value = "Delete a book", key = {"6", "bd"})
    @ShellMethodAvailability(value = "isBooksNotEmpty")
    public void deleteBook() {
        bookController.deleteBook();
    }

    @ShellMethod(value = "Update a book", key = {"7", "bu"})
    @ShellMethodAvailability(value = "isBooksNotEmpty")
    public void updateBook() {
        bookController.updateBook();
    }

    private Availability isBooksNotEmpty() {
        return bookController.isBooksEmpty()
                ? Availability.unavailable(messageService.getMessage("table.has.no.records", "BOOKS"))
                : Availability.available();
    }

}
