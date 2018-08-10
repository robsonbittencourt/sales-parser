package com.github.robsonbittencourt.salesparser.file.domain;

import com.github.robsonbittencourt.salesparser.exception.InvalidLineEntryException;

import java.math.BigDecimal;

class SalesmanLineParser implements LineParser {

    public static final String SEPARATOR = "ç";
    public static final int CPF_POSITION = 1;
    public static final int NAME_POSITION = 2;
    public static final int SALARY_POSITION = 3;

    @Override
    public Salesman parseLine(String line) {
        String[] fields = line.split(SEPARATOR);

        validate(fields);

        return new Salesman(fields[CPF_POSITION], fields[NAME_POSITION], new BigDecimal(fields[SALARY_POSITION]));
    }

    private void validate(String[] fields) {
        validateFieldMatch(fields);
        validateSalary(fields);
    }

    private void validateFieldMatch(String[] fields) {
        int salesmanFieldQuantity = 4;

        if (fields.length != salesmanFieldQuantity) {
            throw new InvalidLineEntryException("The line doesn't match with salesman information");
        }
    }

    private void validateSalary(String[] fields) {
        try {
            new BigDecimal(fields[SALARY_POSITION]);
        } catch (NumberFormatException e) {
            throw new InvalidLineEntryException("Invalid salary: " + fields[SALARY_POSITION]);
        }
    }

}
