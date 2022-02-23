package com.github.steleal.library.service.impl;

import com.github.steleal.library.dao.AuthorDao;
import com.github.steleal.library.dao.BookDao;
import com.github.steleal.library.dao.GenreDao;
import com.github.steleal.library.dao.exception.AuthorNotFoundException;
import com.github.steleal.library.dao.exception.BookNotFoundException;
import com.github.steleal.library.domain.Author;
import com.github.steleal.library.domain.Book;
import com.github.steleal.library.domain.Genre;
import com.github.steleal.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {
    private static final long AUTHOR_ID = 1;
    private static final long GENRE_ID = 2;
    private static final long BOOK_ID = 3;
    private static final Author AUTHOR = new Author(AUTHOR_ID, "Толстой Л.Н.");
    private static final Genre GENRE = new Genre(GENRE_ID, "Роман");
    private static final String TITLE = "Война и Мир";
    private static final Book EXPECTED_BOOK = new Book(BOOK_ID, TITLE, AUTHOR, GENRE);
    private static final Book OTHER_BOOK = new Book(2, "Воскресение", AUTHOR, GENRE);

    @MockBean
    private AuthorDao mockAuthorDao;
    @MockBean
    private GenreDao mockGenreDao;
    @MockBean
    private BookDao mockBookDao;

    @Autowired
    private BookService bookService;

    @Test
    public void testCount() {
        when(mockBookDao.count()).thenReturn(1001L);

        assertThat(bookService.count(), is(1001L));
    }

    @Test
    public void testGetAll() {
        List<Book> expectedBookList = List.of(EXPECTED_BOOK, OTHER_BOOK);
        when(mockBookDao.getAll()).thenReturn(expectedBookList);

        assertThat(bookService.getAll(), is(expectedBookList));
    }

    @Test
    public void testCreate() {
        when(mockAuthorDao.getById(AUTHOR_ID)).thenReturn(AUTHOR);
        when(mockGenreDao.getById(GENRE_ID)).thenReturn(GENRE);
        when(mockBookDao.insert(EXPECTED_BOOK)).thenReturn(BOOK_ID);

        assertThat(bookService.create(TITLE, AUTHOR_ID, GENRE_ID), is(BOOK_ID));
    }

    @Test
    public void testCreateWhenAuthorNotExistThenException() {
        when(mockAuthorDao.getById(AUTHOR_ID)).thenThrow(new AuthorNotFoundException(AUTHOR_ID));
        when(mockGenreDao.getById(GENRE_ID)).thenReturn(GENRE);
        when(mockBookDao.insert(EXPECTED_BOOK)).thenReturn(BOOK_ID);

        assertThrows(AuthorNotFoundException.class,
                () -> bookService.create(TITLE, AUTHOR_ID, GENRE_ID),
                "Expected bookService.create() throw AuthorNotFoundException, but it didn't");
        verify(mockBookDao, times(0)).insert(any());
    }

    @Test
    public void testFindById() {
        when(mockBookDao.getById(BOOK_ID)).thenReturn(EXPECTED_BOOK);

        assertThat(bookService.findById(BOOK_ID), is(EXPECTED_BOOK));
    }

    @Test
    public void testFindByIdWhenBookNotExistsThenException() {
        when(mockBookDao.getById(BOOK_ID)).thenThrow(new BookNotFoundException(BOOK_ID));

        assertThrows(BookNotFoundException.class,
                () -> bookService.findById(BOOK_ID),
                "Expected bookService.findById() throw BookNotFoundException, but it didn't");
    }

    @Test
    public void testUpdate() {
        when(mockAuthorDao.getById(AUTHOR_ID)).thenReturn(AUTHOR);
        when(mockGenreDao.getById(GENRE_ID)).thenReturn(GENRE);
        bookService.update(BOOK_ID, TITLE, AUTHOR_ID, GENRE_ID);

        verify(mockBookDao, times(1)).update(EXPECTED_BOOK);
    }

    @Test
    public void testUpdateWhenBookNotFoundThenException() {
        when(mockAuthorDao.getById(AUTHOR_ID)).thenReturn(AUTHOR);
        when(mockGenreDao.getById(GENRE_ID)).thenReturn(GENRE);
        doThrow(new BookNotFoundException(BOOK_ID))
                .when(mockBookDao).update(EXPECTED_BOOK);

        assertThrows(BookNotFoundException.class,
                () -> bookService.update(BOOK_ID, TITLE, AUTHOR_ID, GENRE_ID),
                "Expected bookService.update() throw BookNotFoundException, but it didn't");
        verify(mockBookDao, times(1)).update(EXPECTED_BOOK);
    }

    @Test
    public void testUpdateWhenAuthorNotFoundThenException() {
        when(mockAuthorDao.getById(AUTHOR_ID)).thenThrow(new AuthorNotFoundException(AUTHOR_ID));
        when(mockGenreDao.getById(GENRE_ID)).thenReturn(GENRE);

        assertThrows(AuthorNotFoundException.class,
                () -> bookService.update(BOOK_ID, TITLE, AUTHOR_ID, GENRE_ID),
                "Expected bookService.update() throw AuthorNotFoundException, but it didn't");
        verify(mockBookDao, times(0)).update(EXPECTED_BOOK);
    }

    @Test
    public void testDelete() {
        bookService.delete(BOOK_ID);

        verify(mockBookDao, times(1)).deleteById(BOOK_ID);
    }

    @Test
    public void testDeleteWhenBookNotExistThenException() {
        doThrow(new BookNotFoundException(BOOK_ID)).when(mockBookDao).deleteById(BOOK_ID);

        assertThrows(BookNotFoundException.class,
                () -> bookService.delete(BOOK_ID),
                "Expected bookService.delete() throw BookNotFoundException, but it didn't");
        verify(mockBookDao, times(1)).deleteById(BOOK_ID);
    }

}
