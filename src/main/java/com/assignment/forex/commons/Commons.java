package com.assignment.forex.commons;

import com.google.common.base.Strings;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Optional;

import static com.assignment.forex.constants.Constants.*;
import static com.assignment.forex.constants.Constants.INFO;
import static com.assignment.forex.constants.ErrorCodes.*;
import static com.assignment.forex.constants.ErrorCodes.ERROR_CODE_202_DESCRIPTION;

public class Commons {
    public static java.sql.Date parseDate(String date) {
        try {
            return new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static java.sql.Timestamp parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean validateDate(String date) {
        return date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$") ? true : false;
    }

    public static boolean validateCurrencyCode(String symbol) {
        return symbol.length() == 3 ? true : false;
    }

    public static boolean validateCSV(String csv) {
        return csv.matches("^[a-zA-Z]{3}(,[a-zA-Z]{3})*((,[a-zA-Z]){3})*$") ? true : false;
    }

    public static LinkedHashMap<String, Object> getErrorResponseObj(String code, int errorCode, String info, String errorCodeDesc) {
        JSONObject currencyListObj = new JSONObject();
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("success", false);
        currencyListObj.put(code, errorCode);
        currencyListObj.put(info, errorCodeDesc);
        response.put("error", currencyListObj);
        return response;
    }

    public static Optional<LinkedHashMap<String, Object>> validateCurrencCodeAndlist(String base, String symbols) {
        if (!validateCurrencyCode(base)) {
            return Optional.of(getErrorResponseObj(CODE, ERROR_CODE_201, INFO, ERROR_CODE_201_DESCRIPTION));
        }
        if (!Strings.isNullOrEmpty(symbols)) {
            if (!validateCSV(symbols)) {
                return Optional.of(getErrorResponseObj(CODE, ERROR_CODE_202, INFO, ERROR_CODE_202_DESCRIPTION));
            }
        }
        return Optional.ofNullable(null);
    }


}
