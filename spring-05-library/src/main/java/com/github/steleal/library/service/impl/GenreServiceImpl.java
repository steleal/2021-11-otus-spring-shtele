package com.github.steleal.library.service.impl;

import com.github.steleal.library.dao.GenreDao;
import com.github.steleal.library.domain.Genre;
import com.github.steleal.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
