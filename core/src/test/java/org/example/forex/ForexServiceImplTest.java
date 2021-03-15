package org.example.forex;

import org.example.forex.services.ExchangeService;
import org.example.forex.services.RapidAPIFixerCurrencyService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ForexServiceImplTest {
    private ForexServiceImpl forexService;
    private ExchangeService exchangeService;

    @Before
    public void setup() {
        exchangeService = mock(RapidAPIFixerCurrencyService.class);
        forexService = new ForexServiceImpl(exchangeService);
    }

    @Test
    public void whenGetAllRatesAtSpecificDate() throws Exception {
        when(exchangeService.ratesByDateInJSON(CurrencyCode.USD,
                LocalDate.of(2020, Month.JANUARY, 1)))
                .thenReturn("{\"success\":true,\"timestamp\":1577923199,\"historical\":true," +
                        "\"base\":\"USD\",\"date\":\"2020-01-01\",\"rates\":{\"RUB\":61.865021," +
                        "\"EUR\":0.891401,\"USD\":1,\"BYN\":2.120406,\"UAH\":23.821673}}");
        Map<CurrencyCode, Double> rates = forexService.allExchangeRatesByDate(
                CurrencyCode.USD,
                LocalDate.of(2020, Month.JANUARY, 1)
        );
        assertEquals(5, rates.entrySet().size());
        assertEquals(61.86, rates.get(CurrencyCode.RUB), 0.006);
        assertEquals(0.89, rates.get(CurrencyCode.EUR), 0.002);
        assertEquals(1, rates.get(CurrencyCode.USD), 0.0001);
        assertEquals(2.12, rates.get(CurrencyCode.BYN), 0.0005);
        assertEquals(23.82, rates.get(CurrencyCode.UAH), 0.002);
    }

    @Test
    public void whenGetAllCurrentRates() throws Exception {
        when(exchangeService.currentRatesInJSON(CurrencyCode.USD))
                .thenReturn(
                        String.format(
                                "{\"success\":true,\"timestamp\":1615641666," +
                                        "\"base\":\"USD\",\"date\":\"%s\",\"rates\":{\"RUB\":73.260373," +
                                        "\"EUR\":0.836593,\"USD\":1,\"BYN\":2.59386,\"UAH\":27.712884}}",
                                LocalDate.now()
                        )
                );
        Map<CurrencyCode, Double> rates = forexService.allCurrentExchangeRates(
                CurrencyCode.USD
        );
        assertEquals(5, rates.entrySet().size());
        assertEquals(73.26, rates.get(CurrencyCode.RUB), 0.0004);
        assertEquals(0.83, rates.get(CurrencyCode.EUR), 0.007);
        assertEquals(1, rates.get(CurrencyCode.USD), 0.0001);
        assertEquals(2.59, rates.get(CurrencyCode.BYN), 0.004);
        assertEquals(27.71, rates.get(CurrencyCode.UAH), 0.003);
    }

    @Test
    public void whenGetAllSpecificRatesAtSpecificDate() throws Exception {
        when(exchangeService.specificRatesByDateInJSON(
                CurrencyCode.USD,
                LocalDate.of(2020, Month.JANUARY, 1),
                CurrencyCode.RUB, CurrencyCode.EUR))
                .thenReturn("{\"success\":true,\"timestamp\":1577923199,\"historical\":true," +
                        "\"base\":\"USD\",\"date\":\"2020-01-01\",\"rates\":{\"RUB\":61.865021," +
                        "\"EUR\":0.891401}}");
        Map<CurrencyCode, Double> rates = forexService.specificExchangeRatesByDate(
                CurrencyCode.USD,
                LocalDate.of(2020, Month.JANUARY, 1),
                CurrencyCode.RUB, CurrencyCode.EUR
        );
        assertEquals(2, rates.entrySet().size());
        assertEquals(61.86, rates.get(CurrencyCode.RUB), 0.006);
        assertEquals(0.89, rates.get(CurrencyCode.EUR), 0.002);
    }

    @Test
    public void whenGetAllCurrentSpecificRates() throws Exception {
        when(exchangeService.currentSpecificRatesInJSON(CurrencyCode.USD, CurrencyCode.UAH, CurrencyCode.BYN))
                .thenReturn(
                        String.format(
                                "{\"success\":true,\"timestamp\":1615641666," +
                                        "\"base\":\"USD\",\"date\":\"%s\",\"rates\":{\"BYN\":2.59386," +
                                        "\"UAH\":27.712884}}",
                                LocalDate.now()
                        )
                );
        Map<CurrencyCode, Double> rates = forexService.currentSpecificExchangeRates(
                CurrencyCode.USD,
                CurrencyCode.UAH, CurrencyCode.BYN
        );
        assertEquals(2, rates.entrySet().size());
        assertEquals(2.59, rates.get(CurrencyCode.BYN), 0.004);
        assertEquals(27.71, rates.get(CurrencyCode.UAH), 0.003);
    }

    @Test
    public void whenGetSpecificRateAtSpecificDate() throws Exception {
        when(exchangeService.specificRateInJSON(CurrencyCode.USD, CurrencyCode.RUB,
                LocalDate.of(2020, Month.JANUARY, 1)))
                .thenReturn("{\"success\":true,\"timestamp\":1577923199,\"historical\":true," +
                        "\"base\":\"USD\",\"date\":\"2020-01-01\",\"rates\":{\"RUB\":61.865021}}");
        double rubRate = forexService.targetRateOfCurrencyByDate(
                CurrencyCode.USD, CurrencyCode.RUB,
                LocalDate.of(2020, Month.JANUARY, 1)
        );
        assertEquals(61.86, rubRate, 0.006);
    }

    @Test
    public void whenGetCurrentSpecificRate() throws Exception {
        when(exchangeService.currentSpecificRateInJSON(CurrencyCode.USD, CurrencyCode.RUB))
                .thenReturn(
                        String.format(
                                "{\"success\":true,\"timestamp\":1615641666," +
                                        "\"base\":\"USD\",\"date\":\"%s\",\"rates\":{\"RUB\":73.260373}}",
                                LocalDate.now()
                        )
                );
        double rubRate = forexService.currentTargetRateOfCurrency(
                CurrencyCode.USD, CurrencyCode.RUB
        );
        assertEquals(73.26, rubRate, 0.0004);
    }

    @Test(expected = Exception.class)
    public void whenTryingSendNullValueArguments() throws Exception {
        when(exchangeService.ratesByDateInJSON(CurrencyCode.USD,
                LocalDate.of(2020, Month.JANUARY, 1)))
                .thenReturn("{\"success\":true,\"timestamp\":1577923199,\"historical\":true," +
                        "\"base\":\"USD\",\"date\":\"2020-01-01\",\"rates\":{\"RUB\":61.865021," +
                        "\"EUR\":0.891401,\"USD\":1,\"BYN\":2.120406,\"UAH\":23.821673}}");
        CurrencyCode code = null;
        Map<CurrencyCode, Double> rates = forexService.allExchangeRatesByDate(
                code,
                LocalDate.of(2020, Month.JANUARY, 1)
        );
    }

    @Test(expected = Exception.class)
    public void whenBadCredentials() throws Exception {
        when(exchangeService.ratesByDateInJSON(CurrencyCode.USD,
                LocalDate.of(2020, Month.JANUARY, 1)))
                .thenReturn("{\"message\":\"Invalid API key. Go to" +
                        " https:\\/\\/docs.rapidapi.com\\/docs\\/keys for more info.\"}");
        Map<CurrencyCode, Double> rates = forexService.allExchangeRatesByDate(
                CurrencyCode.USD,
                LocalDate.of(2020, Month.JANUARY, 1)
        );
    }
}