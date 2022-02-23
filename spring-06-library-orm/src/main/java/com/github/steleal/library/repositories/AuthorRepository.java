package com.github.steleal.library.repositories;

import com.github.steleal.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    long count();

    Author save(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    List<Author> findByFullName(String fullName);

    void deleteById(long id);
}
