package com.github.steleal.library.dao;

import com.github.steleal.library.domain.Genre;

import java.util.List;

public interface GenreDao  {
    long count();

    long insert(Genre genre);

    Genre getById(long id);

    void update(Genre entity);

    void deleteById(long id);

    List<Genre> getAll();
}
