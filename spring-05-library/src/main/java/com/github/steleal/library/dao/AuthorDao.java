package com.github.steleal.library.dao;

import com.github.steleal.library.domain.Author;

import java.util.List;

public interface AuthorDao  {
    long count();

    long insert(Author author);

    Author getById(long id);

    void update(Author author);

    void deleteById(long id);

    List<Author> getAll();
}
