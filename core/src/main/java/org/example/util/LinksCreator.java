package org.example.util;

import java.util.Arrays;
import java.util.StringJoiner;

public class LinksCreator {
    public static String createURL(String host, String date, String base, String... symbols) {
        StringBuilder url = baseCreating(host, date, base);
        if (symbols.length == 0) {
            return url.toString();
        }
        url.append("&symbols=");
        StringJoiner urlBuilder = new StringJoiner(",", url, "");
        Arrays.stream(symbols).forEach(urlBuilder::add);
        return urlBuilder.toString();
    }

    public static String createURL(String host, String date, String base) {
        return baseCreating(host, date, base).toString();
    }

    private static StringBuilder baseCreating(String host, String date, String base) {
        StringBuilder url = new StringBuilder();
        url.append("https://")
                .append(host)
                .append("/")
                .append(date)
                .append("?base=")
                .append(base);
        return url;
    }
}
