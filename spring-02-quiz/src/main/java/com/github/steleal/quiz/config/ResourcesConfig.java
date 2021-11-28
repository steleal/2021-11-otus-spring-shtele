package com.github.steleal.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

@Configuration
@PropertySource("classpath:application.properties")
public class ResourcesConfig {
    @Bean
    public ClassPathResource questionCsvResource(@Value("${questions.csv.path}") String path) {
        return new ClassPathResource(path);
    }

    @Bean
    public int passScore(@Value("${quiz.pass.score}") int passScore) {
        return passScore;
    }

    @Bean
    public int maxScore(@Value("${quiz.max.score}") int maxScore) {
        return maxScore;
    }

    @Bean
    public String helloMessage(@Value("${hello.message}") String helloMessage) {
        return helloMessage;
    }

    @Bean
    public String byeMessage(@Value("${bye.message}") String byeMessage) {
        return byeMessage;
    }
}
