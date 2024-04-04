package com.krautsou.currencyexchange.service.currency;

import com.krautsou.currencyexchange.exception.CurrencyAlreadyExistsException;
import com.krautsou.currencyexchange.model.entity.Currency;
import com.krautsou.currencyexchange.model.request.CurrencySaveRequest;
import com.krautsou.currencyexchange.model.response.CurrencyResponse;
import com.krautsou.currencyexchange.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Test
    void testGetAllCurrency() {

        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency(1L, "USD"));
        currencies.add(new Currency(2L, "EUR"));

        when(currencyRepository.findAll()).thenReturn(currencies);

        List<Currency> result = currencyService.getAllCurrency();

        assertEquals(currencies.size(), result.size());
        assertEquals(currencies.get(0).getCode(), result.get(0).getCode());
        assertEquals(currencies.get(1).getCode(), result.get(1).getCode());
    }

    @Test
    void testSaveNewCurrency() {

        CurrencySaveRequest request = new CurrencySaveRequest("USD");
        Currency savedCurrency = new Currency(1L, "USD");


        when(currencyRepository.findByCode("USD")).thenReturn(null);


        when(currencyRepository.save(any())).thenReturn(savedCurrency);


        CurrencyResponse response = currencyService.save(request);


        assertEquals(savedCurrency.getId(), response.getId());
        assertEquals(savedCurrency.getCode(), response.getCode());


        verify(currencyRepository, times(1)).save(any());
    }

    @Test
    void testSaveExistingCurrency() {

        CurrencySaveRequest request = new CurrencySaveRequest("USD");

        when(currencyRepository.findByCode("USD")).thenReturn(new Currency(1L, "USD"));

        assertThrows(CurrencyAlreadyExistsException.class, () -> currencyService.save(request));

        verify(currencyRepository, times(0)).save(any());
    }
}
