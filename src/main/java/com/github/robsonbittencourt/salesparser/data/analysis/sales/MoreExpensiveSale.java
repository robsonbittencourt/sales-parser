package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.domain.Sale;

public class MoreExpensiveSale extends ReportItem {

    private Sale moreExpensiveSale;

    @Override
    public String description() {
        return "ID da venda mais cara";
    }

    @Override
    public String value() {
        if (moreExpensiveSale == null) {
            return "";
        }

        return moreExpensiveSale.getId().toString();
    }

    @Override
    protected Class<?> dataType() {
        return Sale.class;
    }

    @Override
    protected void processEntry(DataType entry) {
        Sale sale = (Sale) entry;

        if (moreExpensiveSale == null || sale.compareTo(moreExpensiveSale) > 0) {
            moreExpensiveSale = sale;
        }
    }
}
