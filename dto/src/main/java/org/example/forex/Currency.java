package org.example.forex;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class Currency {
    private boolean success;

    private CurrencyCode base;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Map<CurrencyCode, Double> rates = new HashMap<>();

    /**
     * Error message. If success == true, message is null
     */
    private String message;

    @Override
    public String toString() {
        return "Currency{" +
                "success=" + success +
                ", base=" + base +
                ", date=" + date +
                ", rates=" + rates +
                '}';
    }
}
