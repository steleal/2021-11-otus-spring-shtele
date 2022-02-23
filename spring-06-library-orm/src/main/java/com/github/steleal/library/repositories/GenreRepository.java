package com.github.steleal.library.repositories;

import com.github.steleal.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    long count();

    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    List<Genre> findByName(String name);

    void deleteById(long id);
}
