package com.github.robsonbittencourt.salesparser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Customer implements DataType {

    private String cnpj;
    private String name;
    private String businessArea;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cnpj, customer.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

}
