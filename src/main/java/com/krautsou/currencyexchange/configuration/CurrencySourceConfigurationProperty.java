package com.krautsou.currencyexchange.configuration;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "currency")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CurrencySourceConfigurationProperty {

    private String baseUrl;
    private String accessKey;

}
