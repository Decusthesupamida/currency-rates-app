package com.krautsou.currencyexchange.service.rates;

import com.krautsou.currencyexchange.component.FreeCurrencyApiBridge;
import com.krautsou.currencyexchange.model.data.CurrencyRateMap;
import com.krautsou.currencyexchange.model.entity.Currency;
import com.krautsou.currencyexchange.model.response.CurrencyRatesResponse;
import com.krautsou.currencyexchange.service.currency.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyRatesServiceTest {

    @Mock
    private CurrencyService currencyService;

    @Mock
    private FreeCurrencyApiBridge freeCurrencyApiBridge;

    @InjectMocks
    private CurrencyRatesServiceImpl currencyRatesService;


    @Test
    void testUpdateRates() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency(1L, "USD"));
        currencies.add(new Currency(2L, "EUR"));
        when(currencyService.getAllCurrency()).thenReturn(currencies);

        Map<String, Double> ratesData = new HashMap<>();
        ratesData.put("RUB", 0.85);
        ratesData.put("GBP", 0.75);
        CurrencyRatesResponse response1 = CurrencyRatesResponse.builder()
                .data(ratesData)
                .build();
        CurrencyRatesResponse response2 = CurrencyRatesResponse.builder()
                .data(ratesData)
                .build();
        when(freeCurrencyApiBridge.getRatesByBaseCurrencies("USD")).thenReturn(response1);
        when(freeCurrencyApiBridge.getRatesByBaseCurrencies("EUR")).thenReturn(response2);


        currencyRatesService.updateRates();

        // Assert
        CurrencyRateMap currencyRateMap = currencyRatesService.getAllRates();
        assertEquals(2, currencyRateMap.getData().size());
        assertTrue(currencyRateMap.getData().containsKey("USD"));
        assertTrue(currencyRateMap.getData().containsKey("EUR"));
        assertEquals(2, currencyRateMap.getData().get("USD").getRates().size());
        assertEquals(2, currencyRateMap.getData().get("EUR").getRates().size());
    }
}
