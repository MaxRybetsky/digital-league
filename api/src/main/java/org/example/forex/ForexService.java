package org.example.forex;

import java.time.LocalDate;
import java.util.Map;

/**
 * Service for getting info about currencies rates.
 */
public interface ForexService {
    /**
     * Returns Map with currency code as key and
     * base currency rate at specific date as value.
     * Keep all possible World currencies values.
     *
     * @param baseCurrency Base currency to get rates.
     * @param date         Date of rates.
     * @return Map with rates values.
     * @throws Exception If something went wrong.
     */
    Map<CurrencyCode, Double> allExchangeRatesByDate(CurrencyCode baseCurrency, LocalDate date)
            throws Exception;

    /**
     * Returns Map with currency code as key and
     * current base currency rate as value.
     * Keep all possible World currencies values.
     *
     * @param baseCurrency Base currency to get rates.
     * @return Map with rates values.
     * @throws Exception If something went wrong.
     */
    Map<CurrencyCode, Double> allCurrentExchangeRates(CurrencyCode baseCurrency) throws Exception;

    /**
     * Returns Map with currency code as key and
     * base currency rate at specific date as value.
     * Keep only specific currencies rates values.
     *
     * @param baseCurrency Base currency to get rates.
     * @param date         Date of rates.
     * @param targetCodes  Array of required currencies.
     * @return Map with specific currencies rates values.
     * @throws Exception If something went wrong.
     */
    Map<CurrencyCode, Double> specificExchangeRatesByDate(CurrencyCode baseCurrency, LocalDate date,
                                                          CurrencyCode... targetCodes) throws Exception;

    /**
     * Returns Map with currency code as key and
     * current base currency rate as value.
     * Keep only specific currencies rates values.
     *
     * @param baseCurrency Base currency to get rates.
     * @param targetCodes  Array of required currencies.
     * @return Map with specific currencies rates values.
     * @throws Exception If something went wrong.
     */
    Map<CurrencyCode, Double> currentSpecificExchangeRates(CurrencyCode baseCurrency,
                                                           CurrencyCode... targetCodes) throws Exception;

    /**
     * Returns double base currency rate at specific date
     * in relation to the target currency.
     *
     * @param baseCurrency   Base currency to get rate.
     * @param targetCurrency Target currency for getting exchange rate.
     * @param date           Date of rates.
     * @return Current base currency rate at specific date.
     * @throws Exception If something went wrong.
     */
    double targetRateOfCurrencyByDate(CurrencyCode baseCurrency,
                                      CurrencyCode targetCurrency, LocalDate date) throws Exception;

    /**
     * Returns current double base currency rate
     * in relation to the target currency.
     *
     * @param baseCurrency   Base currency to get rate.
     * @param targetCurrency Target currency for getting exchange rate.
     * @return Current base currency rate in relation to the target currency.
     * @throws Exception If something went wrong.
     */
    double currentTargetRateOfCurrency(CurrencyCode baseCurrency,
                                       CurrencyCode targetCurrency) throws Exception;
}
