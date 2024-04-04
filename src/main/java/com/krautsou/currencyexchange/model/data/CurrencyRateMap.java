package com.krautsou.currencyexchange.model.data;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
public class CurrencyRateMap {

    private Map<String, Rates> data;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Rates {
        Map<String, Double> rates;
    }

}
