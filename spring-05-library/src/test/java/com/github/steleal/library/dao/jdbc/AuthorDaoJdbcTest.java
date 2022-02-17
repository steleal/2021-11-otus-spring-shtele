package com.github.steleal.library.dao.jdbc;

import com.github.steleal.library.dao.exception.AuthorNotFoundException;
import com.github.steleal.library.domain.Author;
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
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {
    private static final String FULL_NAME = "Толстой Л.Н.";
    private static final Author AUTHOR = new Author(1, FULL_NAME);
    private static final Author OTHER = new Author(2, "Толстой А.Н.");

    @Autowired
    private AuthorDaoJdbc authorDao;

    @Test
    public void testCount() {
        assertThat(authorDao.count(), is(2L));
    }

    @Test
    public void testGetById() {
        assertThat(authorDao.getById(1), is(AUTHOR));
    }

    @Test
    public void testInsert() {
        assertThat(authorDao.count(), is(2L));
        String fullName = "Носов Н.Н.";
        long id = authorDao.insert(new Author(1, fullName));
        Author expected = new Author(id, fullName);

        assertThat(authorDao.count(), is(3L));
        assertThat(authorDao.getById(id), is(expected));
    }

    @Test
    public void testUpdate() {
        assertThat(authorDao.getById(1), is(AUTHOR));
        String fullName = "Носов Н.Н.";
        Author updated = new Author(1, fullName);
        authorDao.update(updated);

        assertThat(authorDao.getById(1), is(updated));
    }

    @Test
    public void testDeleteById() {
        assertThat(authorDao.getById(1), is(AUTHOR));
        authorDao.deleteById(1);

        assertThat(authorDao.count(), is(1L));
        assertThrows(AuthorNotFoundException.class, () -> authorDao.deleteById(1));
    }

    @Test
    public void testGetAll() {
        assertThat(authorDao.getAll(), is(List.of(AUTHOR, OTHER)));
    }
}