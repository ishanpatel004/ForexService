package com.assignment.forex.service;

import com.assignment.forex.entity.Currency;
import com.assignment.forex.entity.CurrencyRate;
import com.assignment.forex.repositories.CurrencyRateRepository;
import com.assignment.forex.repositories.CurrencyRepository;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.assignment.forex.commons.Commons.*;
import static com.assignment.forex.constants.Constants.CODE;
import static com.assignment.forex.constants.Constants.INFO;
import static com.assignment.forex.constants.ErrorCodes.*;

@Slf4j
@Service
public class ForexService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    public LinkedHashMap<String, Object> getAllCurrencies() {
        List<Currency> currencyList = currencyRepository.fetchAllCurrencies();
        JSONObject currencyListObj = new JSONObject();
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        log.info("Response Size: " + currencyList.size());

        if (currencyList.size() > 0) {
            currencyList.stream().forEach(currency -> currencyListObj.put(currency.getCurrencyCode(), currency.getCurrencyName()));
            response.put("success", true);
            response.put("symbols", currencyListObj);
        } else {
            response = getErrorResponseObj(CODE, ERROR_CODE_106, INFO, ERROR_CODE_106_DESCRIPTION);
        }
        return response;
    }

    public LinkedHashMap<String, Object> getCurrencyRateByDate(String date, String base, String symbols) {
        JSONObject currencyListObj = new JSONObject();
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        if (!validateDate(date)) {
            return new LinkedHashMap<String, Object>(getErrorResponseObj(CODE, ERROR_CODE_302, INFO, ERROR_CODE_302_DESCRIPTION));
        }

        Optional<LinkedHashMap<String, Object>> resp = validateCurrencCodeAndlist(base, symbols);
        if (resp.isPresent()) {
            return new LinkedHashMap<String, Object>(resp.get());
        }


        List<CurrencyRate> currencyRateList;
        if (!Strings.isNullOrEmpty(symbols)) {
            currencyRateList = currencyRateRepository.getCurrencyRateByDateAndCode(parseDate(date), base, Arrays.asList(symbols.split(",")));
        } else {
            currencyRateList = currencyRateRepository.findByCurrencyRateDateAndFromCurrencyCode(parseDate(date), base);
        }
        JSONObject ratesObj = new JSONObject();
        currencyRateList.stream().forEach(currencyRate -> ratesObj.put(currencyRate.getToCurrencyCode(), currencyRate.getEodRate()));
        response.put("success", true);
        //response.put("timestamp", new Timestamp(System.currentTimeMillis()));
        response.put("base", base);
        response.put("date", date);
        if (ratesObj.size() >= 1) {
            response.put("rates", ratesObj);
        } else {
            response = getErrorResponseObj(CODE, ERROR_CODE_106, INFO, ERROR_CODE_106_DESCRIPTION);
        }
        return response;
    }

    public LinkedHashMap<String, Object> getTimeSeriesCurrencyRates(String startDate, String endDate, String base, String symbols) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        if (!validateDate(startDate) || !validateDate(endDate)) {
            return new LinkedHashMap<String, Object>(getErrorResponseObj(CODE, ERROR_CODE_302, INFO, ERROR_CODE_302_DESCRIPTION));
        }

        Optional<LinkedHashMap<String, Object>> resp = validateCurrencCodeAndlist(base, symbols);
        if (resp.isPresent()) {
            return new LinkedHashMap<String, Object>(resp.get());
        }

        List<CurrencyRate> currencyRateList = null;
        Date stDate = parseDate(startDate);
        Date edDate = parseDate(endDate);
        long diffInMillies = Math.abs(edDate.getTime() - stDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        if (diff > 365) {
            return new LinkedHashMap<String, Object>(getErrorResponseObj(CODE, ERROR_CODE_505, INFO, ERROR_CODE_505_DESCRIPTION));
        }

        if (symbols != null) {
            currencyRateList = currencyRateRepository.fetchRatesByDateRangeAndToCurrencyCode(stDate, edDate, base, Arrays.asList(symbols.split(",")));
        } else {
            currencyRateList = currencyRateRepository.fetchRatesByDateRange(parseDate(startDate), parseDate(endDate), base);
        }

        Map<String, JSONObject> ratesPerDateMap = new LinkedHashMap<>();
        currencyRateList.stream().forEach(currencyRate -> {
            JSONObject dt = ratesPerDateMap.get(currencyRate.getCurrencyRateDate().toString());
            if (dt != null) {
                dt.put(currencyRate.getToCurrencyCode(), currencyRate.getEodRate());
            } else {
                JSONObject obj = new JSONObject();
                obj.put(currencyRate.getToCurrencyCode(), currencyRate.getEodRate());
                ratesPerDateMap.put(currencyRate.getCurrencyRateDate().toString(), obj);
            }

        });
        response.put("success", true);
        response.put("timeseries", true);
        response.put("start_date", startDate);
        response.put("end_date", endDate);
        response.put("base", base);
        response.put("rates", ratesPerDateMap);
        return response;
    }

    public LinkedHashMap<String, Object> convertCurrencyRate(String from, String to, Double amount, String date) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        if (!validateCurrencyCode(from) || !validateCurrencyCode(to)) {
            return getErrorResponseObj(CODE, ERROR_CODE_201, INFO, ERROR_CODE_201_DESCRIPTION);
        }

        if (date != null && !validateDate(date)) {
            return getErrorResponseObj(CODE, ERROR_CODE_302, INFO, ERROR_CODE_302_DESCRIPTION);
        }

        CurrencyRate currencyRate = null;
        if (date != null) {
            currencyRate = currencyRateRepository.findByCurrencyRateDateAndFromCurrencyCodeAndToCurrencyCode(parseDate(date), from, to);
        } else {
            currencyRate = currencyRateRepository.findByCurrencyRateDateAndFromCurrencyCodeAndToCurrencyCode(parseDate(new Date(System.currentTimeMillis()).toString()), from, to);
        }
        if (currencyRate != null) {
            response.put("success", true);
            response.put("timestamp", currencyRate.getLastModifiedDate());
            response.put("date", date == null ? new Date(System.currentTimeMillis()) : date);
            response.put("result", currencyRate.getCurrentRate() * amount);
        } else {
            response = getErrorResponseObj(CODE, ERROR_CODE_106, INFO, ERROR_CODE_106_DESCRIPTION);
        }

        return response;
    }
}
