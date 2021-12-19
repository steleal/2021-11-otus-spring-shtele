package com.github.steleal.library.dao.jdbc;

import com.github.steleal.library.dao.exception.BookNotFoundException;
import com.github.steleal.library.domain.Author;
import com.github.steleal.library.domain.Book;
import com.github.steleal.library.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    private static final long EXIST_BOOK_ID = 1;
    private static final long NOT_EXIST_BOOK_ID = 1001;

    @Autowired
    private BookDaoJdbc bookDao;

    @Test
    public void testCount() {
        assertThat(bookDao.count(), is(1L));
        long insertId = bookDao.insert(getGoldenKey());
        assertThat(bookDao.count(), is(2L));
        bookDao.deleteById(insertId);
        assertThat(bookDao.count(), is(1L));
    }

    @Test
    public void testGetById() {
        Book expectedBook = getWarAndPeace();
        Book actualBook = bookDao.getById(EXIST_BOOK_ID);
        assertThat(actualBook, is(expectedBook));
    }

    @Test
    public void testGetByIdWhenNotExistBookThenException() {
        BookNotFoundException thrown = assertThrows(
                BookNotFoundException.class,
                () -> bookDao.getById(NOT_EXIST_BOOK_ID),
                "Expected bookDao.getById(2) throw BookNotFoundException, but it didn't");
        String expectedErrorMessage = String.format(BookNotFoundException.ERROR_MESSAGE, NOT_EXIST_BOOK_ID);
        assertThat(thrown.getMessage(), is(expectedErrorMessage));
    }

    @Test
    public void testInsertBook() {
        Book expectedBook = getGoldenKey();
        long insertId = bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(insertId);
        assertThat(actualBook, is(expectedBook));
    }

    @Test
    public void testInsertBookWhenNotExistAuthorThenException() {
        Book bookWithNotExistAuthor = getGoldenKey().toBuilder().author(new Author(1001, "Шарль Перро")).build();

        assertThrows(DataIntegrityViolationException.class,
                () -> bookDao.insert(bookWithNotExistAuthor),
                "Expected bookDao.insert() throw DataIntegrityViolationException, but it didn't");
    }

    @Test
    public void testInsertBookWhenNoTitleThenException() {
        Book bookWithNoTitle = getGoldenKey().toBuilder().title(null).build();

        assertThrows(DataIntegrityViolationException.class,
                () -> bookDao.insert(bookWithNoTitle),
                "Expected bookDao.insert() throw DataIntegrityViolationException, but it didn't");
    }

    @Test
    public void testUpdateBook() {
        Book beforeUpdate = bookDao.getById(EXIST_BOOK_ID);
        assertThat(beforeUpdate, is(getWarAndPeace()));

        Book bookWithNewTitle = getWarAndPeace().toBuilder().title("Воскресенье").build();
        bookDao.update(bookWithNewTitle);
        Book actualBook = bookDao.getById(EXIST_BOOK_ID);
        assertThat(actualBook, is(bookWithNewTitle));
    }

    @Test
    public void testDeleteBook() {
        Book deletedBook = bookDao.getById(EXIST_BOOK_ID);
        bookDao.deleteById(deletedBook.getId());
        assertThrows(BookNotFoundException.class,
                () -> bookDao.deleteById(deletedBook.getId()),
                "Expected bookDao.deleteById() throw BookNotFoundException, but it didn't");
    }

    @Test
    public void testGetAllBooks() {
        List<Book> expectedBookList = List.of(getWarAndPeace(), getGoldenKey());
        bookDao.insert(getGoldenKey());
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList, is(expectedBookList));
    }

    private Book getWarAndPeace() {
        return Book.builder()
                .id(1)
                .title("Война и Мир")
                .author(new Author(1, "Толстой Л.Н."))
                .genre(new Genre(1, "Роман"))
                .build();
    }

    private Book getGoldenKey() {
        return Book.builder()
                .id(2)
                .title("Золотой ключик")
                .author(new Author(2, "Толстой А.Н."))
                .genre(new Genre(2, "Сказка"))
                .build();
    }

}
