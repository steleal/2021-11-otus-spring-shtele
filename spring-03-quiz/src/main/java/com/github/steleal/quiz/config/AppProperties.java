package com.github.steleal.quiz.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String questionCsvPath;
    private int quizScorePass;
    private int quizScoreMax;
    private String locale;
}
