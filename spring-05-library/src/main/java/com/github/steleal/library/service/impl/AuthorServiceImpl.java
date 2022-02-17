package com.github.steleal.library.service.impl;

import com.github.steleal.library.dao.AuthorDao;
import com.github.steleal.library.domain.Author;
import com.github.steleal.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
