package com.github.steleal.library.dao.jdbc;

import com.github.steleal.library.dao.AuthorDao;
import com.github.steleal.library.dao.exception.AuthorNotFoundException;
import com.github.steleal.library.domain.Author;
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
public class AuthorDaoJdbc implements AuthorDao {
    private static final RowMapper<Author> AUTHOR_ROW_MAPPER = (rs, rowNum) ->
            new Author(rs.getLong("id"), rs.getString("full_name"));

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long count() {
        Integer count = jdbc.queryForObject(
                "select count(*) from authors",
                Collections.emptyMap(),
                Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("full_name", author.getFullName());

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into authors (full_name) values (:full_name)",
                params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Author getById(long id) {
        try {
            return jdbc.queryForObject("select id, full_name from authors where id = :id",
                    Map.of("id", id),
                    AUTHOR_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new AuthorNotFoundException(id);
        }
    }

    @Override
    public void update(Author author) {
        long count = jdbc.update("update authors set full_name = :full_name where id = :id",
                Map.of("id", author.getId(), "full_name", author.getFullName()));
        if (count != 1) throw new AuthorNotFoundException(author.getId());
    }

    @Override
    public void deleteById(long id) {
        long count = jdbc.update("delete from authors where id = :id", Map.of("id", id));
        if (count != 1) throw new AuthorNotFoundException(id);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, full_name from authors", AUTHOR_ROW_MAPPER);
    }

}
