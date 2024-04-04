package com.krautsou.currencyexchange.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krautsou.currencyexchange.initializer.PostgresInitializer;
import com.krautsou.currencyexchange.model.entity.Currency;
import com.krautsou.currencyexchange.repository.CurrencyRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ExtendWith({SpringExtension.class})
@Testcontainers
@ContextConfiguration(initializers = {
        PostgresInitializer.class,
})
public class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected CurrencyRepository currencyRepository;

    protected void resetData() {
        currencyRepository.deleteAllInBatch();
    }

}
