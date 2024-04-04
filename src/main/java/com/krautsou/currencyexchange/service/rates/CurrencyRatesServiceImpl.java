package com.krautsou.currencyexchange.service.rates;

import com.krautsou.currencyexchange.component.FreeCurrencyApiBridge;
import com.krautsou.currencyexchange.model.data.CurrencyRateMap;
import com.krautsou.currencyexchange.model.entity.Currency;
import com.krautsou.currencyexchange.model.response.CurrencyRatesResponse;
import com.krautsou.currencyexchange.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyRatesServiceImpl implements CurrencyRatesService {

    private final CurrencyService currencyService;
    private final FreeCurrencyApiBridge freeCurrencyApiBridge;
    private final CurrencyRateMap currencyRateMap = new CurrencyRateMap(new HashMap<>());

    @Override
    public CurrencyRateMap getAllRates() {
        return currencyRateMap;
    }

    @Override
    @Scheduled(cron = "${scheduling.cron}")
    public void updateRates() {
        log.info("Starting update rates");

        currencyService.getAllCurrency().stream()
                .map(Currency::getCode)
                .forEach(this::fillRates);

        log.info("Ending update rates");
    }

    private void fillRates(String currency) {
        log.info("Fill currency " + currency);
        CurrencyRatesResponse ratesByBaseCurrencies = freeCurrencyApiBridge.getRatesByBaseCurrencies(currency);

        currencyRateMap.getData().put(currency, new CurrencyRateMap.Rates(ratesByBaseCurrencies.getData()));
    }
}
