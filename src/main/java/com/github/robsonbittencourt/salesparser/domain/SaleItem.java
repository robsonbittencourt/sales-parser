package com.github.robsonbittencourt.salesparser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class SaleItem implements DataType {

    private Long id;
    private int quantity;
    private BigDecimal price;

    public BigDecimal getTotalValue() {
        return price.multiply(new BigDecimal(quantity));
    }

}
