package com.github.steleal.library.dao.exception;

public class AuthorNotFoundException extends RuntimeException {
    public static String ERROR_MESSAGE = "Author with id %s not found";

    public AuthorNotFoundException(long authorId) {
        super(String.format(ERROR_MESSAGE, authorId));
    }
}
