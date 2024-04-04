package com.krautsou.currencyexchange.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRatesResponse {

    @JsonProperty("data")
    private Map<String, Double> data;
}
