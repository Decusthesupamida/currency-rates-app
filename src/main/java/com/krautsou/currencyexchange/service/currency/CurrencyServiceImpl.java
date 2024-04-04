package com.krautsou.currencyexchange.service.currency;

import com.krautsou.currencyexchange.exception.CurrencyAlreadyExistsException;
import com.krautsou.currencyexchange.model.entity.Currency;
import com.krautsou.currencyexchange.model.request.CurrencySaveRequest;
import com.krautsou.currencyexchange.model.response.CurrencyResponse;
import com.krautsou.currencyexchange.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    @Override
    public CurrencyResponse save(CurrencySaveRequest currencySaveRequest) {
        String upperCode = currencySaveRequest.getCode().toUpperCase(Locale.ROOT);
        Currency byCode = currencyRepository.findByCode(upperCode);


        if (byCode == null) {
            Currency saveEntity = Currency.builder()
                    .code(upperCode)
                    .build();

            Currency saved = currencyRepository.save(saveEntity);

            return CurrencyResponse.builder()
                    .id(saved.getId())
                    .code(saved.getCode())
                    .build();
        } else {
            throw new CurrencyAlreadyExistsException(upperCode);
        }
    }
}
