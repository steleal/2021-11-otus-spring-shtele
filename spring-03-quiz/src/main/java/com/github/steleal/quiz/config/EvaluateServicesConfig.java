package com.github.steleal.quiz.config;

import com.github.steleal.quiz.domain.QuestionType;
import com.github.steleal.quiz.service.EvaluateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class EvaluateServicesConfig {
    private final List<EvaluateService> evaluateServices;

    @Bean
    public Map<QuestionType, EvaluateService> evaluateServiceMap() {
        Map<QuestionType, EvaluateService> map = new HashMap<>();
        for (EvaluateService evaluateService : evaluateServices) {
            map.put(evaluateService.getType(), evaluateService);
        }
        return map;
    }
}
