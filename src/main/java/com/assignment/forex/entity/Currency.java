package com.assignment.forex.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "currency")
@Data
public class Currency {
    @Id
    @Column(name = "currencyCode", nullable = false)
    private String currencyCode;

    @Column(name = "currencyName", nullable = false)
    private String currencyName;

    @Column(name = "lastModifiedDate", nullable = false)
    private Timestamp lastModifiedDate;

    public Currency(String currencyCode, String currencyName) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
    }
}
