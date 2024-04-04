package com.krautsou.currencyexchange.component;

import com.krautsou.currencyexchange.configuration.CurrencySourceConfigurationProperty;
import com.krautsou.currencyexchange.model.response.CurrencyRatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class FreeCurrencyApiBridge {


    private final WebClient fixedClient;
    private final CurrencySourceConfigurationProperty property;


    public CurrencyRatesResponse getRatesByBaseCurrencies(String baseCurrencies) {
        return
                fixedClient.get()
                .uri(uriBuilder -> uriBuilder.path("/latest")
                        .queryParam("apikey", UriUtils.encodeQueryParam(property.getAccessKey(), StandardCharsets.UTF_8))
                        .queryParam("base_currency", baseCurrencies)
                        .build())
                .retrieve()
                .bodyToMono(CurrencyRatesResponse.class)
                .block();


    }
}
