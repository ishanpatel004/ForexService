package com.assignment.forex.repositories;

import com.assignment.forex.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    @Query("select new com.assignment.forex.entity.Currency(c.currencyCode, c.currencyName) from Currency c")
    List<Currency> fetchAllCurrencies();
}
