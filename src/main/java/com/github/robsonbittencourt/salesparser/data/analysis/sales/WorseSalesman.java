package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.domain.Sale;

public class WorseSalesman extends ReportItem {

    private Sale worseSale;

    @Override
    public String description() {
        return "O pior vendedor";
    }

    @Override
    public String value() {
        if (worseSale == null) {
            return "";
        }

        return worseSale.getSalesmanName();
    }

    @Override
    protected Class<?> dataType() {
        return Sale.class;
    }

    @Override
    protected void processEntry(DataType entry) {
        Sale sale = (Sale) entry;

        if (worseSale == null || sale.compareTo(worseSale) < 0) {
            worseSale = sale;
        }
    }
}
