package com.github.steleal.library.dao.jdbc;

import com.github.steleal.library.dao.GenreDao;
import com.github.steleal.library.dao.exception.GenreNotFoundException;
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
public class GenreDaoJdbc implements GenreDao {
    private static final RowMapper<Genre> GENRE_ROW_MAPPER = (rs, rowNum) ->
            new Genre(rs.getLong("id"), rs.getString("name"));

    private final NamedParameterJdbcOperations jdbc;


    @Override
    public long count() {
        Integer count = jdbc.queryForObject("select count(*) from genres", Collections.emptyMap(), Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", genre.getName());
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update("insert into genres(name) values (:name)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public Genre getById(long id) {
        try {
            return jdbc.queryForObject("select id, name from genres where id = :id",
                    Map.of("id", id), GENRE_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new GenreNotFoundException(id);
        }
    }

    @Override
    public void update(Genre genre) {
        long count = jdbc.update("update genres set name = :name where id = :id",
                Map.of("id", genre.getId(), "name", genre.getName()));
        if (count != 1) throw new GenreNotFoundException(genre.getId());
    }

    @Override
    public void deleteById(long id) {
        long count = jdbc.update("delete from genres where id = :id", Map.of("id", id));
        if (count != 1) throw new GenreNotFoundException(id);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genres", GENRE_ROW_MAPPER);
    }

}
