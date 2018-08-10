package com.github.robsonbittencourt.salesparser.file.domain;

import java.util.ArrayList;
import java.util.List;

public class SaleParser extends AbstractLineParser {

    public static final int ID_POSITION = 1;
    public static final int SALE_ITEMS_POSITION = 2;
    public static final int SALESMAN_NAME_POSITION = 3;

    @Override
    public Sale parseLine(String line) {
        Long id = getLong(line, ID_POSITION);
        List<SaleItem> items = getItems(line);
        String price = getString(line, SALESMAN_NAME_POSITION);

        return new Sale(id, items, price);
    }

    private List<SaleItem> getItems(String line) {
        List<SaleItem> items = new ArrayList<>();

        String itemsString = getString(line, SALE_ITEMS_POSITION);

        itemsString = itemsString.replace("[", "").replace("]", "");

        LineParser lineParser = new SaleItemParser();
        for (String item : itemsString.split(",")) {
            items.add((SaleItem) lineParser.parseLine(item));
        }

        return items;
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
