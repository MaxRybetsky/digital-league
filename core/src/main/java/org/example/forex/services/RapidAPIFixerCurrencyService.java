package org.example.forex.services;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.example.forex.CurrencyCode;
import org.example.util.LinksCreator;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

/**
 * Exchange rates source: https://rapidapi.com/
 * API name: Fixer Currency.
 */
public class RapidAPIFixerCurrencyService implements ExchangeService {
    private final String apiToken;
    private final String host;

    private final String LATEST = "latest";

    private final Properties properties = new Properties();

    public RapidAPIFixerCurrencyService() throws IOException {
        loadProperties();
        this.apiToken = properties.getProperty("rapid.apiToken");
        this.host = properties.getProperty("rapid.host");
    }

    public RapidAPIFixerCurrencyService(String apiToken, String host) {
        this.apiToken = apiToken;
        this.host = host;
    }

    private void loadProperties() throws IOException {
        try (InputStream in = RapidAPIFixerCurrencyService.class.getClassLoader()
                .getResourceAsStream("rapid-api.properties")) {
            properties.load(in);
        } catch (IOException exception) {
            throw new IOException(exception);
        }
    }

    @Override
    public String ratesByDateInJSON(CurrencyCode baseCurrency, LocalDate date)
            throws IOException, IllegalArgumentException {
        checkArgsForNulls(baseCurrency, date);
        String url = LinksCreator.createURL(host, date.toString(), baseCurrency.name());
        return getJSONFrom(url);
    }

    @Override
    public String currentRatesInJSON(CurrencyCode baseCurrency)
            throws IOException, IllegalArgumentException {
        checkArgsForNulls(baseCurrency);
        String url = LinksCreator.createURL(host, LATEST, baseCurrency.name());
        return getJSONFrom(url);
    }

    @Override
    public String specificRatesByDateInJSON(CurrencyCode baseCurrency, LocalDate date,
                                            CurrencyCode... targetCodes)
            throws IOException, IllegalArgumentException {
        checkArgsForNulls(baseCurrency, date, targetCodes);
        String[] stringTargetCodes = codesToStrings(targetCodes);
        String url = LinksCreator.createURL(host, date.toString(), baseCurrency.name(), stringTargetCodes);
        return getJSONFrom(url);
    }

    @Override
    public String currentSpecificRatesInJSON(CurrencyCode baseCurrency,
                                             CurrencyCode... targetCodes)
            throws IOException, IllegalArgumentException {
        checkArgsForNulls(baseCurrency, targetCodes);
        String[] stringTargetCodes = codesToStrings(targetCodes);
        String url = LinksCreator.createURL(host, LATEST, baseCurrency.name(), stringTargetCodes);
        return getJSONFrom(url);
    }

    @Override
    public String specificRateInJSON(CurrencyCode baseCurrency, CurrencyCode targetCurrency,
                                     LocalDate date) throws IOException, IllegalArgumentException {
        checkArgsForNulls(baseCurrency, targetCurrency, date);
        String url = LinksCreator.createURL(host, date.toString(), baseCurrency.name(), targetCurrency.name());
        return getJSONFrom(url);
    }

    @Override
    public String currentSpecificRateInJSON(CurrencyCode baseCurrency, CurrencyCode targetCurrency)
            throws IOException, IllegalArgumentException {
        checkArgsForNulls(baseCurrency, targetCurrency);
        String url = LinksCreator.createURL(host, LATEST, baseCurrency.name(), targetCurrency.name());
        return getJSONFrom(url);
    }

    private void checkArgsForNulls(Object... args) throws IllegalArgumentException {
        for (Object arg : args) {
            try {
                Objects.requireNonNull(arg, "Argument shouldn't be null!");
            } catch (NullPointerException exception) {
                throw new IllegalArgumentException(exception.getMessage());
            }
        }
    }

    private String[] codesToStrings(CurrencyCode... targetCodes) {
        return Arrays.stream(targetCodes).map(Enum::name).toArray(String[]::new);
    }

    private String getJSONFrom(String stringURL) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(stringURL)
                .get()
                .addHeader("x-rapidapi-key", apiToken)
                .addHeader("x-rapidapi-host", host)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
