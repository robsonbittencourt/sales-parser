package com.github.robsonbittencourt.salesparser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Customer implements DataType {

    private String cnpj;
    private String name;
    private String businessArea;

}
