package com.assignment.forex.service;

import com.assignment.forex.entity.CurrencyRate;
import com.assignment.forex.repositories.CurrencyRateRepository;
import com.assignment.forex.repositories.CurrencyRepository;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.assignment.forex.constants.Constants.CODE;
import static com.assignment.forex.constants.ErrorCodes.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ForexServiceTest {
    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private CurrencyRateRepository currencyRateRepository;

    @InjectMocks
    private ForexService forexService;

    List<CurrencyRate> currencyRateList = new ArrayList<>();


    @Before
    public void setUp() {
        currencyRateList = new ArrayList<>();
        CurrencyRate currencyRate1 = new CurrencyRate();
        currencyRate1.setCurrencyRateId(1L);
        currencyRate1.setFromCurrencyCode("EUR");
        currencyRate1.setToCurrencyCode("INR");
        currencyRate1.setEodRate(2d);
        currencyRate1.setCurrentRate(2.01d);
        currencyRate1.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
        currencyRate1.setCurrencyRateDate(new Date(System.currentTimeMillis()));
        currencyRateList.add(currencyRate1);
    }

    @Test
    public void getCurrencyRateByDate() throws Exception {
        when(currencyRateRepository.findByCurrencyRateDateAndFromCurrencyCode(ArgumentMatchers.any(), ArgumentMatchers.any(String.class))).thenReturn(currencyRateList);
        LinkedHashMap<String, Object> resp = forexService.getCurrencyRateByDate("2021-07-16", "EUR", null);
        JSONObject ratesObj = (JSONObject) resp.get("rates");
        Assertions.assertEquals(ratesObj.size(), 1);
    }

    @Test
    public void getCurrencyRateByDateWithSymbols() throws Exception {
        when(currencyRateRepository.getCurrencyRateByDateAndCode(ArgumentMatchers.any(), ArgumentMatchers.any(String.class), ArgumentMatchers.any())).thenReturn(currencyRateList);
        LinkedHashMap<String, Object> resp = forexService.getCurrencyRateByDate("2021-07-16", "EUR", "AED,INR");
        JSONObject ratesObj = (JSONObject) resp.get("rates");
        Assertions.assertEquals(ratesObj.size(), 1);
    }


    @Test
    public void getCurrencyRateByDateWithInValidDateAsInput() throws Exception {
        LinkedHashMap<String, Object> resp = forexService.getCurrencyRateByDate("2021-17-16", "EUR", "INR,AUD");
        JSONObject ratesObj = (JSONObject) resp.get("error");
        Assertions.assertEquals(ratesObj.get(CODE), ERROR_CODE_302);
    }

    @Test
    public void getCurrencyRateByDateWithInValidBaseCurrencyAsInput() throws Exception {
        LinkedHashMap<String, Object> resp = forexService.getCurrencyRateByDate("2021-07-16", "EURO", "INR,AUD");
        JSONObject ratesObj = (JSONObject) resp.get("error");
        Assertions.assertEquals(ratesObj.get(CODE), ERROR_CODE_201);
    }

    @Test
    public void getCurrencyRateByDateWithNonExistingCurrency() throws Exception {
        currencyRateList = new ArrayList<>();
        when(currencyRateRepository.getCurrencyRateByDateAndCode(ArgumentMatchers.any(), ArgumentMatchers.any(String.class), ArgumentMatchers.any())).thenReturn(currencyRateList);
        LinkedHashMap<String, Object> resp = forexService.getCurrencyRateByDate("2021-07-16", "EUR", "AAA");
        JSONObject ratesObj = (JSONObject) resp.get("error");
        Assertions.assertEquals(ratesObj.get(CODE), ERROR_CODE_106);
    }


}
