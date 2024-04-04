package com.krautsou.currencyexchange.service.currency;

import com.krautsou.currencyexchange.model.entity.Currency;
import com.krautsou.currencyexchange.model.request.CurrencySaveRequest;
import com.krautsou.currencyexchange.model.response.CurrencyResponse;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrency();

    CurrencyResponse save(CurrencySaveRequest currencySaveRequest);
}
