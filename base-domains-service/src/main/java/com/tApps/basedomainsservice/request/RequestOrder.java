package com.tApps.basedomainsservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrder {

    private String name;
    private Integer qty;
    private BigDecimal price;
}
