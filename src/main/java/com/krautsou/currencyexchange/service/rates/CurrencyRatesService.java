package com.krautsou.currencyexchange.service.rates;

import com.krautsou.currencyexchange.model.data.CurrencyRateMap;

public interface CurrencyRatesService {
    CurrencyRateMap getAllRates();
    void updateRates();
}
