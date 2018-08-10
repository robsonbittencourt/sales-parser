package com.github.robsonbittencourt.salesparser.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Salesman implements DataType {

    private String cpf;
    private String name;
    private BigDecimal salary;

}
