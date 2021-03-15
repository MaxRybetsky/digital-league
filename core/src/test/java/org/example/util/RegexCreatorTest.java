package org.example.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegexCreatorTest {
    @Test
    public void whenCreatingSimpleStartsWithRegex() {
        String[] letters = {"f", "E"};
        String expected = "\\b[FfEe][a-zA-Zа-яА-Я]*";
        String result = RegexCreator.createWordStartsWithRegex(letters);
        assertEquals(expected, result);
    }

    @Test
    public void whenCreatingSimpleStartsWithRegexWithRussianLetters() {
        String[] letters = {"а", "Ф"};
        String expected = "\\b[АаФф][a-zA-Zа-яА-Я]*";
        String result = RegexCreator.createWordStartsWithRegex(letters);
        assertEquals(expected, result);
    }
}