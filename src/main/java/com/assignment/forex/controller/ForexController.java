package com.assignment.forex.controller;

import com.assignment.forex.exception.EndPointNotFoundException;
import com.assignment.forex.service.ForexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static com.assignment.forex.constants.ErrorCodes.ERROR_CODE_103_DESCRIPTION;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashMap;


@CrossOrigin
@Validated
@Slf4j
@Api(tags = { "Foreign Exchange Service Client" })
@RestController
@RequestMapping("/api")
public class ForexController {

    @Autowired
    private ForexService forexService;


    @ApiOperation(value = "API to fetch list of all available currencies")
    @GetMapping("/currencies")
    public LinkedHashMap<String, Object> fetchAllCurrencies() {
        log.info("Request received to fetch all currencies");
        return forexService.getAllCurrencies();
    }


    @ApiOperation(value = "API to fetch exchange rate at a particular day")
    @GetMapping("/date")
    public LinkedHashMap<String, Object> fetchCurrencyRateByDate(
            @ApiParam(name = "date", type = "String", value = "A date in the past for which historical rates are requested. Date Format:YYYY-MM-DD", example = "2021-07-14", required = true)
            @RequestParam(name="date", required = false) String date,
            @ApiParam(name = "base", type = "String", value = "Enter the three-letter currency code of your preferred base currency", example = "EUR", required = true)
            @RequestParam(required = false) String base,
            @ApiParam(name = "symbols", type = "String", value = "Enter a list of comma-separated currency codes to limit output currencies.", example = "EUR,INR", required = false)
            @RequestParam(required = false) String symbols) {

        log.info("Request received to fetch exchange rate at a particular day. Date: {}", date + "Base Currency: {}", base + " Symbols: {}", symbols);
        return forexService.getCurrencyRateByDate(date, base, symbols);
    }


    @ApiOperation(value = "API to fetch exchange rates at all available dates as a collection")
    @GetMapping("/timeseries")
    public LinkedHashMap<String, Object> fetchTimeSeriesCurrencyRates(
            @ApiParam(name = "start_date", type = "String", value = "The start date of your preferred timeframe. Date Format:YYYY-MM-DD", example = "2020-12-01", required = true)
            @RequestParam(name = "start_date", required = false) String startDate,
            @ApiParam(name = "end_date", type = "String", value = "The end date of your preferred timeframe. Date Format:YYYY-MM-DD", example = "2021-12-01", required = true)
            @RequestParam(name = "end_date", required = false) String endDate,
            @ApiParam(name = "base", type = "String", value = "Enter the three-letter currency code of your preferred base currency", example = "EUR", required = true)
            @RequestParam(required = false) String base,
            @ApiParam(name = "symbols", type = "String", value = "Enter a list of comma-separated currency codes to limit output currencies.", example = "EUR,INR", required = false)
            @RequestParam(required = false) String symbols) {

        log.info("Request received to  fetch exchange rates at all available dates as a collection. Start Date: {}", startDate + "End Date: {}", endDate + " Base: {}", base + " Symbols: {}", symbols);
        return forexService.getTimeSeriesCurrencyRates(startDate, endDate, base, symbols);
    }


    @ApiOperation(value = "API to convert any amount from one currency to another")
    @GetMapping("/convert")
    public LinkedHashMap<String, Object> convertCurrencyRate(
            @ApiParam(name = "from", type = "String", value = "The three-letter currency code of the currency you would like to convert from.", example = "EUR", required = true)
            @RequestParam(required = false) String from,
            @ApiParam(name = "to", type = "String", value = "The three-letter currency code of the currency you would like to convert to.", example = "INR", required = true)
            @RequestParam(required = false) String to,
            @ApiParam(name = "amount", type = "String", value = "The amount to be converted.", example = "100", required = true)
            @RequestParam(required = false) String amount,
            @ApiParam(name = "date", type = "String", value = "Specify a date (format YYYY-MM-DD) to use historical rates for this conversion.", example = "100", required = false)
            @RequestParam(required = false) String date) {

        log.info("Request received to fetch exchange rates at all available dates as a collection. From Currency code: {}", from + "To Currency Code: {}", to + " Amount: {}", amount + " Date: {}", date);
        return forexService.convertCurrencyRate(from, to, amount, date);
    }

    @ApiIgnore
    @RequestMapping(value = "/**")
    public String fetchAnythingelse() {
        throw new EndPointNotFoundException(ERROR_CODE_103_DESCRIPTION);
    }
}
