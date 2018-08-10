package com.github.robsonbittencourt.salesparser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Sale implements DataType {

    private Long id;
    private List<SaleItem> items;
    private String name;

}
