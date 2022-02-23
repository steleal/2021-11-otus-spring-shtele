package com.github.steleal.library.repositories;

import com.github.steleal.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    long count();

    Book save(Book genre);

    Optional<Book> findById(long id);

    List<Book> findAll();

    List<Book> findByTitle(String title);

    void deleteById(long id);
}
