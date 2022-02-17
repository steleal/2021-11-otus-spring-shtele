package com.github.steleal.library.controller.converter;

import com.github.steleal.library.domain.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToStringConverter implements Converter<Book, String> {
    @Override
    public String convert(Book b) {
        return b.getId() + "\t" + b.getTitle() + "\t" + b.getAuthor().getFullName() + "\t" + b.getGenre().getName();
    }
}
