package com.github.steleal.library.service.impl;

import com.github.steleal.library.dao.AuthorDao;
import com.github.steleal.library.dao.BookDao;
import com.github.steleal.library.dao.GenreDao;
import com.github.steleal.library.domain.Author;
import com.github.steleal.library.domain.Book;
import com.github.steleal.library.domain.Genre;
import com.github.steleal.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public long create(String title, long authorId, long genreId) {
        Book book = Book.builder()
                .title(title)
                .author(authorDao.getById(authorId))
                .genre(genreDao.getById(genreId))
                .build();
        return bookDao.insert(book);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book findById(long bookId) {
        return bookDao.getById(bookId);
    }

    @Override
    public void update(long bookId, String title, long authorId, long genreId) {
        Book book = Book.builder()
                .id(bookId)
                .title(title)
                .author(authorDao.getById(authorId))
                .genre(genreDao.getById(genreId))
                .build();
        bookDao.update(book);
    }

    @Override
    public void delete(long bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    public long count() {
        return bookDao.count();
    }
}
