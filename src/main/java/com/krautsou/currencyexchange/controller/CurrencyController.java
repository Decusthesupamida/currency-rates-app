package com.krautsou.currencyexchange.controller;

import com.krautsou.currencyexchange.model.data.CurrencyRateMap;
import com.krautsou.currencyexchange.model.entity.Currency;
import com.krautsou.currencyexchange.model.request.CurrencySaveRequest;
import com.krautsou.currencyexchange.model.response.CurrencyResponse;
import com.krautsou.currencyexchange.service.currency.CurrencyService;
import com.krautsou.currencyexchange.service.rates.CurrencyRatesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.krautsou.currencyexchange.controller.CurrencyController.PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PATH)
public class CurrencyController {
    public static final String PATH = "/api/currencies";
    private final CurrencyService currencyService;
    private final CurrencyRatesService currencyRatesService;


    @Operation(method = "get", description = "Get all currencies")
    @GetMapping
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        List<Currency> currencyList = currencyService.getAllCurrency();

        return ResponseEntity.ok(currencyList);
    }

    @Operation(method = "post", description = "Add new currency")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CurrencyResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Currency already exist",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<CurrencyResponse> addCurrency(@RequestBody CurrencySaveRequest currencySaveRequest) {
        CurrencyResponse response = currencyService.save(currencySaveRequest);

        return ResponseEntity.ok(response);
    }

    @Operation(method = "get", description = "Get all latest currency rates")
    @GetMapping("/latest")
    public ResponseEntity<CurrencyRateMap> getLatestCurrencyRates() {
        CurrencyRateMap allRates = currencyRatesService.getAllRates();

        return ResponseEntity.ok(allRates);
    }


}
