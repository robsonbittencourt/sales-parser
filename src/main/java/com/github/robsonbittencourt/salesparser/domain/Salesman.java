package com.github.robsonbittencourt.salesparser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Salesman implements DataType {

    private String cpf;
    private String name;
    private BigDecimal salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salesman salesman = (Salesman) o;
        return Objects.equals(cpf, salesman.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
