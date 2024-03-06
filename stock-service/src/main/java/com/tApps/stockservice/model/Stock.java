package com.tApps.stockservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "stock_details")
@Data
@NoArgsConstructor@AllArgsConstructor
public class Stock {

    @Id
    private String id;

    private String name;

    private Integer qty;

    private BigDecimal price;
}
