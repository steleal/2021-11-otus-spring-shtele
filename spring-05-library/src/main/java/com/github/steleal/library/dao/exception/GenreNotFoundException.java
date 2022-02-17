package com.github.steleal.library.dao.exception;

public class GenreNotFoundException extends RuntimeException {
    public static String ERROR_MESSAGE = "Genre with id %s not found";

    public GenreNotFoundException(long genreId) {
        super(String.format(ERROR_MESSAGE, genreId));
    }
}
