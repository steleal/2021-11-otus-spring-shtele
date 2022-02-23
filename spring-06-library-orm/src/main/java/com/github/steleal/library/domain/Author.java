package com.github.steleal.library.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static lombok.EqualsAndHashCode.Exclude;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class Author {
    @Exclude
    private final long id;
    private final String fullName;
}
