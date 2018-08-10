package com.github.robsonbittencourt.salesparser.file.domain;

import java.math.BigDecimal;

class SaleItemParser extends AbstractLineParser implements LineParser {

    public static final int ID_POSITION = 0;
    public static final int QUANTITY_POSITION = 1;
    public static final int PRICE_POSITION = 2;

    @Override
    public SaleItem parseLine(String line) {
        Long id = getLong(line, ID_POSITION);
        int quantity = getInteger(line, QUANTITY_POSITION);
        BigDecimal price = getBigDecimal(line, PRICE_POSITION);

        return new SaleItem(id, quantity, price);
    }

    @Override
    protected String separator() {
        return "-";
    }

    @Override
    protected int fieldsQuantity() {
        return 3;
    }

}
