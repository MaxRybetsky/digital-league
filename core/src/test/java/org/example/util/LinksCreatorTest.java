package org.example.util;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class LinksCreatorTest {
    private String host;
    private String base;

    @Before
    public void before() {
        host = "host.com";
        base = "RUB";
    }

    @Test
    public void whenCreateLinkForLatestRatesWithoutSymbols() {
        String expected = "https://host.com/latest?base=RUB";
        String result = LinksCreator.createURL(host, "latest", base);
        assertEquals(expected, result);
    }

    @Test
    public void whenCreateLinkForSpecificDateRatesWithoutSymbols() {
        String expected = "https://host.com/2020-11-11?base=RUB";
        LocalDate localDate = LocalDate.of(2020, Month.NOVEMBER, 11);
        String result = LinksCreator.createURL(host, localDate.toString(), base);
        assertEquals(expected, result);
    }

    @Test
    public void whenCreateLinkForLatestRatesWithSymbols() {
        String expected = "https://host.com/latest?base=RUB&symbols=USD,EUR,GBP";
        String result = LinksCreator.createURL(host, "latest", base, "USD", "EUR", "GBP");
        assertEquals(expected, result);
    }

    @Test
    public void whenCreateLinkForSpecificDateRatesWithSymbols() {
        String expected = "https://host.com/2020-11-11?base=RUB&symbols=USD,EUR,GBP";
        LocalDate localDate = LocalDate.of(2020, Month.NOVEMBER, 11);
        String result = LinksCreator.createURL(host, localDate.toString(), base,"USD", "EUR", "GBP");
        assertEquals(expected, result);
    }
}