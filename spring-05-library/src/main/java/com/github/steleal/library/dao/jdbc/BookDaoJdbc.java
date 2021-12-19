package com.github.steleal.library.dao.jdbc;

import com.github.steleal.library.dao.BookDao;
import com.github.steleal.library.dao.exception.BookNotFoundException;
import com.github.steleal.library.domain.Author;
import com.github.steleal.library.domain.Book;
import com.github.steleal.library.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, rowNum) -> new Book(
            rs.getLong("id"),
            rs.getString("title"),
            new Author(rs.getLong("author_id"), rs.getString("full_name")),
            new Genre(rs.getLong("genre_id"), rs.getString("name")));

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long count() {
        Integer count = jdbc.queryForObject("select count(*) from books", Collections.emptyMap(), Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId());

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into books (title, author_id, genre_id) values (:title, :author_id, :genre_id)",
                params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Book getById(long id) {
        try {
            return jdbc.queryForObject(
                    "select b.id,\n" +
                            "       b.title,\n" +
                            "       b.author_id,\n" +
                            "       b.genre_id,\n" +
                            "       a.full_name,\n" +
                            "       g.name\n" +
                            "from books b\n" +
                            "         join authors a on b.author_id = a.id\n" +
                            "         join genres g on b.genre_id = g.id\n" +
                            "where b.id = :id",
                    Map.of("id", id),
                    BOOK_ROW_MAPPER
            );
        } catch (DataAccessException e) {
            throw new BookNotFoundException(id);
        }
    }

    @Override
    public void update(Book book) {
        int count = jdbc.update("update books set title = :title, author_id = :author_id, genre_id = :genre_id where id = :id",
                Map.of("id", book.getId(),
                        "title", book.getTitle(),
                        "author_id", book.getAuthor().getId(),
                        "genre_id", book.getGenre().getId()));
        if (count != 1) throw new BookNotFoundException(book.getId());
    }

    @Override
    public void deleteById(long id) {
        long count = jdbc.update("delete from books where id = :id", Map.of("id", id));
        if (count != 1) throw new BookNotFoundException(id);
    }

    @Override
    public List<Book> getAll() {
        return jdbc.getJdbcOperations().query(
                "select b.id,\n" +
                        "       b.title,\n" +
                        "       b.author_id,\n" +
                        "       b.genre_id,\n" +
                        "       a.full_name,\n" +
                        "       g.name\n" +
                        "from books b\n" +
                        "         join authors a on b.author_id = a.id\n" +
                        "         join genres g on b.genre_id = g.id\n",
                BOOK_ROW_MAPPER
        );
    }
}
