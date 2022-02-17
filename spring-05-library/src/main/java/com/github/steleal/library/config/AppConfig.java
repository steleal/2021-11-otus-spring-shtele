package com.github.steleal.library.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final AppProperties appProps;

    @Bean
    public Locale locale() {
        return Locale.forLanguageTag(getLocaleOrDefault());
    }

    private String getLocaleOrDefault() {
        return appProps.getLocale() == null
                ? "en"
                : appProps.getLocale();
    }

}
