package com.github.steleal.quiz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {
    private final AppProperties appProps;

    @Bean
    public ClassPathResource questionCsvResource() {
        String localizedPath = getLocaleOrDefault() + "/" + appProps.getQuestionCsvPath();
        return new ClassPathResource(localizedPath);
    }

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
