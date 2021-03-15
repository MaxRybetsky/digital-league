package org.example.forex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.forex.services.ExchangeService;

import java.time.LocalDate;
import java.util.Map;

public class ForexServiceImpl implements ForexService {
    private final ExchangeService exchangeService;
    private final ObjectMapper mapper;

    public ForexServiceImpl(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public Map<CurrencyCode, Double> allExchangeRatesByDate(CurrencyCode baseCurrency, LocalDate date)
            throws Exception {
        String json = exchangeService.ratesByDateInJSON(baseCurrency, date);
        Currency currency = mapper.readValue(json, Currency.class);
        checkForSuccess(currency);
        return currency.getRates();
    }

    @Override
    public Map<CurrencyCode, Double> allCurrentExchangeRates(CurrencyCode baseCurrency) throws Exception {
        String json = exchangeService.currentRatesInJSON(baseCurrency);
        Currency currency = mapper.readValue(json, Currency.class);
        checkForSuccess(currency);
        return currency.getRates();
    }

    @Override
    public Map<CurrencyCode, Double> specificExchangeRatesByDate(CurrencyCode baseCurrency, LocalDate date,
                                                                 CurrencyCode... targetCodes) throws Exception {
        String json = exchangeService.specificRatesByDateInJSON(baseCurrency, date, targetCodes);
        Currency currency = mapper.readValue(json, Currency.class);
        checkForSuccess(currency);
        return currency.getRates();
    }

    @Override
    public Map<CurrencyCode, Double> currentSpecificExchangeRates(CurrencyCode baseCurrency,
                                                                  CurrencyCode... targetCodes) throws Exception {
        String json = exchangeService.currentSpecificRatesInJSON(baseCurrency, targetCodes);
        Currency currency = mapper.readValue(json, Currency.class);
        checkForSuccess(currency);
        return currency.getRates();
    }

    @Override
    public double targetRateOfCurrencyByDate(CurrencyCode baseCurrency, CurrencyCode targetCurrency,
                                             LocalDate date) throws Exception {
        String json = exchangeService.specificRateInJSON(baseCurrency, targetCurrency, date);
        Currency currency = mapper.readValue(json, Currency.class);
        checkForSuccess(currency);
        return currency.getRates().get(targetCurrency);
    }

    @Override
    public double currentTargetRateOfCurrency(CurrencyCode baseCurrency, CurrencyCode targetCurrency) throws Exception {
        String json = exchangeService.currentSpecificRateInJSON(baseCurrency, targetCurrency);
        Currency currency = mapper.readValue(json, Currency.class);
        checkForSuccess(currency);
        return currency.getRates().get(targetCurrency);
    }

    /**
     * Checks if rate(s) was(were) successfully received.
     *
     * @param currency Currency to check.
     * @throws Exception There was an error receiving rates.
     */
    private void checkForSuccess(Currency currency) throws Exception {
        if (!currency.isSuccess()) {
            throw new Exception(currency.getMessage());
        }
    }
}
