package org.example.util;

import java.util.Arrays;

public class RegexCreator {
    public static String createWordStartsWithRegex(String... letters) {
        String regexStart = "\\b[";
        String regexEnd = "][a-zA-Zа-яА-Я]*";
        StringBuilder regex = new StringBuilder(regexStart);
        Arrays.stream(letters).forEach(
                letter -> {
                    regex.append(letter.toUpperCase());
                    regex.append(letter.toLowerCase());
                }
        );
        regex.append(regexEnd);
        return regex.toString();
    }
}
