package com.github.steleal.library.repositories.exception;

public class BookNotFoundException extends RuntimeException {
    public static String ERROR_MESSAGE = "Book with id %s not found";

    public BookNotFoundException(long bookId) {
        super(String.format(ERROR_MESSAGE, bookId));
    }
}
