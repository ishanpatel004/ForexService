package com.assignment.forex.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

import static com.assignment.forex.commons.Commons.getErrorResponseObj;
import static com.assignment.forex.constants.Constants.CODE;
import static com.assignment.forex.constants.Constants.INFO;
import static com.assignment.forex.constants.ErrorCodes.*;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public LinkedHashMap<String, Object> handle() {
        return getErrorResponseObj(CODE, ERROR_CODE_500, INFO, ERROR_CODE_500_DESCRIPTION);
    }

    @ExceptionHandler(EndPointNotFoundException.class)
    @ResponseBody
    public LinkedHashMap<String, Object> handleCustomException(EndPointNotFoundException ex) {
        return getErrorResponseObj(CODE, ERROR_CODE_103, INFO, ERROR_CODE_103_DESCRIPTION);
    }

}
