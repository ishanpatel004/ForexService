package com.assignment.forex.repositories;

import com.assignment.forex.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    List<CurrencyRate> findByCurrencyRateDateAndFromCurrencyCode(Date date, String fromCurencyCode);

    @Query("SELECT cr FROM  CurrencyRate cr WHERE cr.currencyRateDate=:date AND cr.fromCurrencyCode=:base AND cr.toCurrencyCode IN :symbols")
    List<CurrencyRate> getCurrencyRateByDateAndCode(Date date, String base, Collection<String> symbols);

    @Query("SELECT cr FROM  CurrencyRate cr WHERE cr.currencyRateDate between :startDate AND :endDate AND cr.fromCurrencyCode=:base")
    List<CurrencyRate> fetchRatesByDateRange(Date startDate, Date endDate, String base);

    @Query("SELECT cr FROM  CurrencyRate cr WHERE cr.currencyRateDate between :startDate AND :endDate AND cr.fromCurrencyCode=:base AND cr.toCurrencyCode IN :symbols")
    List<CurrencyRate> fetchRatesByDateRangeAndToCurrencyCode(Date startDate, Date endDate, String base, Collection<String> symbols);

    CurrencyRate findByCurrencyRateDateAndFromCurrencyCodeAndToCurrencyCode(Date parseDate, String from, String to);
}