package com.github.steleal.library.service;

import com.github.steleal.library.domain.Book;

import java.util.List;

public interface BookService {
    long create(String title, long authorId, long genreId);

    List<Book> getAll();

    Book findById(long bookId);

    void update(long bookId, String title,long authorId, long genreId);

    void delete(long bookId);

    long count();
}
