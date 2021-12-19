package com.github.steleal.quiz.util;

import java.util.Objects;

public class StringUtils {
    public static boolean isIntNumber(String str) {
        if (Objects.isNull(str) || str.isBlank()) return false;

        String testNumber = str.trim();
        for (char c : testNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
