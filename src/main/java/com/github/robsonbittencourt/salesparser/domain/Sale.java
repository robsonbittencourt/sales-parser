package com.github.robsonbittencourt.salesparser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class Sale implements DataType, Comparable<Sale> {

    private Long id;
    private List<SaleItem> items;
    private String name;

    public BigDecimal getSaleValue() {
        return items.stream()
                .map(SaleItem::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int compareTo(Sale sale) {
        return this.getSaleValue().compareTo(sale.getSaleValue());
    }
}
