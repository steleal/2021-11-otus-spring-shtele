package com.github.steleal.library.dao.jdbc;

import com.github.steleal.library.dao.exception.GenreNotFoundException;
import com.github.steleal.library.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Positive cases only!
 */
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {
    private static final String NAME = "Роман";
    private static final Genre FIRST = new Genre(1, NAME);
    private static final Genre SECOND = new Genre(2, "Сказка");

    @Autowired
    private GenreDaoJdbc genreDao;

    @Test
    public void testCount() {
        assertThat(genreDao.count(), is(2L));
    }

    @Test
    public void testGetById() {
        assertThat(genreDao.getById(1), is(FIRST));
    }

    @Test
    public void testInsert() {
        String name = "Поэма";
        assertThat(genreDao.count(), is(2L));
        Genre inserted = new Genre(1, name);
        long id = genreDao.insert(inserted);
        Genre expected = new Genre(id, name);

        assertThat(genreDao.count(), is(3L));
        assertThat(genreDao.getById(id), is(expected));
    }

    @Test
    public void testUpdate() {
        assertThat(genreDao.getById(1), is(FIRST));
        String name = "Поэма";
        Genre updated = new Genre(1, name);
        genreDao.update(updated);

        assertThat(genreDao.getById(1), is(updated));
    }

    @Test
    public void testDeleteById() {
        assertThat(genreDao.getById(1), is(FIRST));
        genreDao.deleteById(1);

        assertThat(genreDao.count(), is(1L));
        assertThrows(GenreNotFoundException.class, () -> genreDao.deleteById(1));
    }

    @Test
    public void testGetAll() {
        assertThat(genreDao.getAll(), is(List.of(FIRST, SECOND)));
    }
}