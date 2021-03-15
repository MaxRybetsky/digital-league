package org.example.forex.services;

import org.example.forex.CurrencyCode;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Remote source of exchange rates.
 */
public interface ExchangeService {
    /**
     * Gets from source (e.g. REST API service) all base currency
     * rates at specific date and returns them as JSON String.
     *
     * @param baseCurrency Currency to get rates.
     * @param date         Specific date of rates.
     * @return String with all base currency rates at specific date
     * in JSON.
     * @throws IOException              If connection was interrupted.
     * @throws IllegalArgumentException If arguments were incorrect
     *                                  (e.g. equal {@code null}).
     */
    String ratesByDateInJSON(CurrencyCode baseCurrency, LocalDate date)
            throws IOException, IllegalArgumentException;

    /**
     * Gets from source (e.g. REST API service) all base currency
     * rates at current time and returns them as JSON String.
     *
     * @param baseCurrency Currency to get rates.
     * @return String with all current base currency rates
     * in JSON.
     * @throws IOException              If connection was interrupted.
     * @throws IllegalArgumentException If arguments were incorrect
     *                                  (e.g. equal {@code null}).
     */
    String currentRatesInJSON(CurrencyCode baseCurrency)
            throws IOException, IllegalArgumentException;

    /**
     * Gets from source (e.g. REST API service) base currency
     * rates in relation to specific currencies at specific date
     * and returns them as JSON String.
     *
     * @param baseCurrency Currency to get rates.
     * @param date         Specific date of rates.
     * @param targetCodes  Target currencies to get rates.
     * @return String with specific base currency rates at specific date
     * in JSON.
     * @throws IOException              If connection was interrupted.
     * @throws IllegalArgumentException If arguments were incorrect
     *                                  (e.g. equal {@code null}).
     */
    String specificRatesByDateInJSON(CurrencyCode baseCurrency, LocalDate date,
                                     CurrencyCode... targetCodes)
            throws IOException, IllegalArgumentException;

    /**
     * Gets from source (e.g. REST API service) base currency
     * rates in relation to specific currencies at current time
     * and returns them as JSON String.
     *
     * @param baseCurrency Currency to get rates.
     * @param targetCodes  Target currencies to get rates.
     * @return String with specific base currency rates at current time
     * in JSON.
     * @throws IOException              If connection was interrupted.
     * @throws IllegalArgumentException If arguments were incorrect
     *                                  (e.g. equal {@code null}).
     */
    String currentSpecificRatesInJSON(CurrencyCode baseCurrency, CurrencyCode... targetCodes)
            throws IOException, IllegalArgumentException;

    /**
     * Gets from source (e.g. REST API service) base currency
     * rate in relation to specific currency at specific date
     * and returns it as JSON String.
     *
     * @param baseCurrency   Currency to get rate.
     * @param targetCurrency Target currency to get rate.
     * @param date           Specific date of rate.
     * @throws IOException              If connection was interrupted.
     * @throws IllegalArgumentException If arguments were incorrect
     *                                  (e.g. equal {@code null}).
     */
    String specificRateInJSON(CurrencyCode baseCurrency,
                              CurrencyCode targetCurrency, LocalDate date)
            throws IOException, IllegalArgumentException;

    /**
     * Gets from source (e.g. REST API service) base currency
     * rate in relation to specific currency at current time
     * and returns it as JSON String.
     *
     * @param baseCurrency   Currency to get rate.
     * @param targetCurrency Target currency to get rate.
     * @throws IOException              If connection was interrupted.
     * @throws IllegalArgumentException If arguments were incorrect
     *                                  (e.g. equal {@code null}).
     */
    String currentSpecificRateInJSON(CurrencyCode baseCurrency,
                                     CurrencyCode targetCurrency)
            throws IOException, IllegalArgumentException;
}
