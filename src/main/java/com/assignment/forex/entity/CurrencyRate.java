package com.assignment.forex.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Table(name = "currency_rate") //, uniqueConstraints = @UniqueConstraint(columnNames = {"currencyRateDate", "fromCurrencyCode", "toCurrencyCode"}))
@Data
@Entity
public class CurrencyRate {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long currencyRateId;

    @Column(nullable = false)
    private java.sql.Date currencyRateDate;

    @Column(nullable = false)
    private String fromCurrencyCode;

    @Column(nullable = false)
    private String toCurrencyCode;

    @Column(nullable = false)
    private Double currentRate;

    @Column(nullable = false)
    private Double eodRate;

    @Column(nullable = false)
    private Timestamp lastModifiedDate;
}
