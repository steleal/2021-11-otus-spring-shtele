package com.github.steleal.library.repositories;

import com.github.steleal.library.domain.Comment;

import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(long id);

    void deleteById(long id);
}
