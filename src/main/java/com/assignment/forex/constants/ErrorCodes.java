package com.assignment.forex.constants;

public class ErrorCodes {
    public static final int ERROR_CODE_404 = 404;
    public static final String ERROR_CODE_404_DESCRIPTION = "The requested resource does not exist.";

    public static final int ERROR_CODE_101 = 101;
    public static final String ERROR_CODE_101_DESCRIPTION = "No API Key was specified or an invalid API Key was specified.";

    public static final int ERROR_CODE_103 = 103;
    public static final String ERROR_CODE_103_DESCRIPTION = "The requested API endpoint does not exist.";

    public static final int ERROR_CODE_104 = 104;
    public static final String ERROR_CODE_104_DESCRIPTION = "The maximum allowed API amount of monthly API requests has been reached.";

    public static final int ERROR_CODE_105 = 105;
    public static final String ERROR_CODE_105_DESCRIPTION = "The current subscription plan does not support this API endpoint.";

    public static final int ERROR_CODE_106 = 106;
    public static final String ERROR_CODE_106_DESCRIPTION = "The current request did not return any results.";

    public static final int ERROR_CODE_102 = 102;
    public static final String ERROR_CODE_102_DESCRIPTION = "The account this API request is coming from is inactive.";

    public static final int ERROR_CODE_201 = 201;
    public static final String ERROR_CODE_201_DESCRIPTION = "An invalid base currency has been entered.";

    public static final int ERROR_CODE_202 = 202;
    public static final String ERROR_CODE_202_DESCRIPTION = "One or more invalid symbols have been specified.";

    public static final int ERROR_CODE_203 = 203;
    public static final String ERROR_CODE_203_DESCRIPTION = "No base currency has been specified.";

    public static final int ERROR_CODE_301 = 301;
    public static final String ERROR_CODE_301_DESCRIPTION = "No date has been specified.";

    public static final int ERROR_CODE_302 = 302;
    public static final String ERROR_CODE_302_DESCRIPTION = "An invalid date has been specified.";

    public static final int ERROR_CODE_403 = 403;
    public static final String ERROR_CODE_403_DESCRIPTION = "No or an invalid amount has been specified.";

    public static final int ERROR_CODE_500 = 500;
    public static final String ERROR_CODE_500_DESCRIPTION = "Sorry your specific request is currently inaccessible. Please try an alternative method or contact customer support for assistance.";

    public static final int ERROR_CODE_501 = 501;
    public static final String ERROR_CODE_501_DESCRIPTION = "No or an invalid timeframe has been specified.";

    public static final int ERROR_CODE_502 = 502;
    public static final String ERROR_CODE_502_DESCRIPTION = "No or an invalid `start_date` has been specified.";

    public static final int ERROR_CODE_503 = 503;
    public static final String ERROR_CODE_503_DESCRIPTION = "No or an invalid `end_date` has been specified.";

    public static final int ERROR_CODE_504 = 504;
    public static final String ERROR_CODE_504_DESCRIPTION = "An invalid timeframe has been specified.";

    public static final int ERROR_CODE_505 = 505;
    public static final String ERROR_CODE_505_DESCRIPTION = "The specified timeframe is too long, exceeding 365 days.";

    public static final int ERROR_CODE_506 = 506;
    public static final String ERROR_CODE_506_DESCRIPTION = "No or an invalid `From` currency has been specified.";

    public static final int ERROR_CODE_507 = 507;
    public static final String ERROR_CODE_507_DESCRIPTION = "No or an invalid `To` currency has been specified.";

}
