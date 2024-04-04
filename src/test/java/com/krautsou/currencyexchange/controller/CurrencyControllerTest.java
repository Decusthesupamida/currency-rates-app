package com.krautsou.currencyexchange.controller;

import com.krautsou.currencyexchange.model.entity.Currency;
import com.krautsou.currencyexchange.model.request.CurrencySaveRequest;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.krautsou.currencyexchange.controller.CurrencyController.PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurrencyControllerTest extends BaseControllerTest {

    @BeforeEach
    void resetAll() {
        resetData();
    }


    @Test
    @Order(1)
    void getCurrencies_ReturnsCurrencies() throws Exception {
        saveCurrency(1L, "RUB");
        saveCurrency(2L, "EUR");


        List<Currency> expectedList = List.of(
                new Currency(1L, "RUB"),
                new Currency(2L, "EUR")
        );

        mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedList)));
    }

    @Test
    @Order(2)
    void saveCurrency_ReturnsSaveCurrency() throws Exception {
        CurrencySaveRequest expectRequest = CurrencySaveRequest.builder()
                .code("GBP")
                .build();


        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(expectRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(expectRequest.getCode()));
    }

    @Test
    @Order(3)
    void saveCurrency_ReturnsBadRequest() throws Exception {
        saveCurrency(1L, "USD");

        CurrencySaveRequest expectRequest = CurrencySaveRequest.builder()
                .code("USD")
                .build();


        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding(StandardCharsets.UTF_8)
                        .content(objectMapper.writeValueAsString(expectRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private void saveCurrency(Long id, String code) {
        currencyRepository.save(new Currency(id, code));
    }

}
