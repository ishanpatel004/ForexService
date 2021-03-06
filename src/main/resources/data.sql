INSERT INTO currency(currency_code, currency_name, last_modified_Date)
VALUES('USD', 'US DOLLAR', CURRENT_TIMESTAMP),
('INR', 'Indian Rupee', CURRENT_TIMESTAMP),
('EUR', 'Euro', CURRENT_TIMESTAMP),
('AED', 'United Arab Emirates Dirham', CURRENT_TIMESTAMP),
('GBP', 'British Pound', CURRENT_TIMESTAMP),
('AUD', 'Australian Dollar', CURRENT_TIMESTAMP),
('CAD', 'Canadian Dollar', CURRENT_TIMESTAMP),
('SGD', 'Singapore Dollar', CURRENT_TIMESTAMP),
('CHF', 'Swiss Franc', CURRENT_TIMESTAMP),
('MYR', 'Malaysian Ringgit', CURRENT_TIMESTAMP),
('JPY', 'Japanese Yen', CURRENT_TIMESTAMP),
('CNY', 'Chinese Yuan Renminbi', CURRENT_TIMESTAMP),
('THB', 'Thailand Baht', CURRENT_TIMESTAMP);

INSERT INTO currency_rate(currency_rate_id, currency_rate_date, from_currency_code, to_currency_code, current_rate, eod_rate, last_modified_Date)
VALUES(1,TO_DATE('2021-07-11', 'yyyy-MM-dd'), 'EUR', 'JPY', 6.9, 0.98, CURRENT_TIMESTAMP),
(2,CURRENT_DATE, 'EUR', 'USD', 0.3, 5.98, CURRENT_TIMESTAMP),
(3,CURRENT_DATE, 'EUR', 'GBP', 0.49, 4.98, CURRENT_TIMESTAMP),
(4,CURRENT_DATE, 'EUR', 'INR', 0.69, 2.98, CURRENT_TIMESTAMP),
(5,CURRENT_DATE, 'EUR', 'CHF', 0.19, 1.98, CURRENT_TIMESTAMP),
(6,CURRENT_DATE, 'EUR', 'AED', 0.29, 2.98, CURRENT_TIMESTAMP),
(7,CURRENT_DATE, 'EUR', 'AUD', 0.59, 7.98, CURRENT_TIMESTAMP),
(8,CURRENT_DATE, 'EUR', 'CAD', 0.69, 8.98, CURRENT_TIMESTAMP),
(9,CURRENT_DATE, 'EUR', 'MYR', 10.9, 9.98, CURRENT_TIMESTAMP),
(10,CURRENT_DATE, 'EUR', 'SGD', 30.9, 10.98, CURRENT_TIMESTAMP),
(11,CURRENT_DATE, 'EUR', 'CNY', 4.9, 1.98, CURRENT_TIMESTAMP),
(12,CURRENT_DATE, 'EUR', 'THB', 5.9, 2.98, CURRENT_TIMESTAMP),
(13,CURRENT_DATE, 'USD', 'EUR', 6.9, 30.98, CURRENT_TIMESTAMP),
(14,CURRENT_DATE, 'USD', 'THB', 3.39, 20.98, CURRENT_TIMESTAMP),
(16,CURRENT_DATE, 'GBP', 'EUR', 0.49, 4.98, CURRENT_TIMESTAMP),
(17,CURRENT_DATE, 'INR', 'EUR', 0.69, 2.98, CURRENT_TIMESTAMP),
(18,CURRENT_DATE, 'CHF', 'EUR', 0.19, 1.98, CURRENT_TIMESTAMP),
(19,CURRENT_DATE, 'AED', 'EUR', 0.29, 2.98, CURRENT_TIMESTAMP),
(20,CURRENT_DATE, 'AUD', 'EUR', 0.59, 7.98, CURRENT_TIMESTAMP),
(21,CURRENT_DATE, 'CAD', 'EUR', 0.69, 8.98, CURRENT_TIMESTAMP),
(22,CURRENT_DATE, 'MYR', 'EUR', 10.9, 9.98, CURRENT_TIMESTAMP),
(23,CURRENT_DATE, 'SGD', 'EUR', 30.9, 10.98, CURRENT_TIMESTAMP),
(24,CURRENT_DATE, 'CNY', 'EUR', 4.9, 1.98, CURRENT_TIMESTAMP),
(25,CURRENT_DATE, 'THB', 'EUR', 5.9, 2.98, CURRENT_TIMESTAMP),
(26,CURRENT_DATE, 'THB', 'EUR', 3.39, 20.98, CURRENT_TIMESTAMP);








