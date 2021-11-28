package com.github.steleal.quiz.service.impl.converter;

import com.github.steleal.quiz.util.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StringToAnswerNumSetConverter implements Converter<String, Set<Integer>> {
    @Override
    public Set<Integer> convert(String source) {
        String[] tokens = source.trim().replace(" ", ",").split(",");
        return Arrays.stream(tokens)
                .filter(t -> t != null && !t.isBlank())
                .filter(StringUtils::isIntNumber)
                .map(Integer::parseUnsignedInt)
                .collect(Collectors.toSet());
    }

}
