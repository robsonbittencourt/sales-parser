package com.github.robsonbittencourt.salesparser.file.parser;

import com.github.robsonbittencourt.salesparser.domain.Customer;

final class CustomerLineParser extends AbstractLineParser {

    public static final int CNPJ_POSITION = 1;
    public static final int NAME_POSITION = 2;
    public static final int BUSINESS_AREA_POSITION = 3;

    @Override
    public Customer parseLine(String line) {
        String cnpj = getString(line, CNPJ_POSITION);
        String name = getString(line, NAME_POSITION);
        String businessArea = getString(line, BUSINESS_AREA_POSITION);

        return new Customer(cnpj, name, businessArea);
    }

    @Override
    protected String separator() {
        return "ç";
    }

    @Override
    protected int fieldsQuantity() {
        return 4;
    }

}
