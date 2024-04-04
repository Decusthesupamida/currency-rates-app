package com.krautsou.currencyexchange.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Configuration
public class CurrencyWebClientConfiguration {

    private final CurrencySourceConfigurationProperty property;

    @Bean("fixedClient")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(property.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
