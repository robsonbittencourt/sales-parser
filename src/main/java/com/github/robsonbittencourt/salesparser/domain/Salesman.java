package com.github.robsonbittencourt.salesparser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Salesman implements DataType {

    private String cpf;
    private String name;
    private BigDecimal salary;

}
