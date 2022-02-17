package com.github.steleal.library.service.impl;

import com.github.steleal.library.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageServiceImp implements MessageService {
    private final MessageSource messageSource;
    private final Locale locale;

    @Override
    public String getMessage(String code, String... args) {
        return messageSource.getMessage(code, args, locale);
    }

}
