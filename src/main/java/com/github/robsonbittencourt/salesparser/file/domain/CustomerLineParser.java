package com.github.robsonbittencourt.salesparser.file.domain;

import com.github.robsonbittencourt.salesparser.exception.InvalidLineEntryException;

import java.math.BigDecimal;

class CustomerLineParser implements LineParser {

    public static final String SEPARATOR = "ç";
    public static final int CNPJ_POSITION = 1;
    public static final int NAME_POSITION = 2;
    public static final int BUSINESS_AREA_POSITION = 3;

    @Override
    public Customer parseLine(String line) {
        String[] fields = line.split(SEPARATOR);

        validateFieldMatch(fields);

        return new Customer(fields[CNPJ_POSITION], fields[NAME_POSITION], fields[BUSINESS_AREA_POSITION]);
    }

    private void validateFieldMatch(String[] fields) {
        int customerFieldQuantity = 4;

        if (fields.length != customerFieldQuantity) {
            throw new InvalidLineEntryException("The line doesn't match with customer information");
        }
    }

}
