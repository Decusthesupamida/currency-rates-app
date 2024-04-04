package com.krautsou.currencyexchange.repository;

import com.krautsou.currencyexchange.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findByCode(String code);
}
