package com.assignment.forex.controller;

import com.assignment.forex.entity.Currency;
import com.assignment.forex.entity.CurrencyRate;
import com.assignment.forex.service.ForexService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ForexController.class)
public class ForexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForexService forexService;

    private ForexController forexController;

    List<CurrencyRate> currencyRateList = new ArrayList<>();

    @Before
    public void setUp() {
        forexController = new ForexController();
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
    public void fetchAllCurrenciesTest() throws Exception {
        JSONObject currencyListObj = new JSONObject();
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        List<Currency> currencyList = new ArrayList<>();
        Currency currency1 = new Currency("EUR", "Euro");
        Currency currency2 = new Currency("INR", "Indian Rupee");
        currencyList.add(currency1);
        currencyList.add(currency2);

        currencyList.stream().forEach(currency -> currencyListObj.put(currency.getCurrencyCode(), currency.getCurrencyName()));
        response.put("success", true);
        response.put("symbols", currencyListObj);
        when(forexService.getAllCurrencies()).thenReturn(response);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/currencies").accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        MockHttpServletResponse resp = result.getResponse();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(resp.getContentAsString());
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("symbols");
        Assertions.assertEquals(jsonObject1.size(), 2);
    }

    @Test
    public void getCurrencyRateByDate() throws Exception {
        JSONObject ratesObj = new JSONObject();
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        currencyRateList.stream().forEach(currencyRate -> ratesObj.put(currencyRate.getToCurrencyCode(), currencyRate.getEodRate()));
        response.put("success", true);
        response.put("base", "EUR");
        response.put("date", "2021-07-14");
        response.put("rates", ratesObj);
        when(forexService.getCurrencyRateByDate(ArgumentMatchers.any(), ArgumentMatchers.any(String.class), ArgumentMatchers.any())).thenReturn(response);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/2021-07-14?base=EUR&symbols=AED,AUD").accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse resp = result.getResponse();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(resp.getContentAsString());
        Assertions.assertTrue((Boolean) jsonObject.get("success"));
    }


}
