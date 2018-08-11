package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.domain.Sale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MoreExpensiveSale extends SalesReportItem {

    private static final int SALE_ID_POSITION = 0;
    private static final int SALE_VALUE_POSITION = 1;

    @Value("${consolidated.data.separator}")
    private String separator;

    private String moreExpensiveSaleId;
    private BigDecimal moreExpensiveSaleValue;

    @Override
    public String description() {
        return "ID da venda mais cara";
    }

    @Override
    public String value() {
        return moreExpensiveSaleId;
    }

    @Override
    public String allValues() {
        return moreExpensiveSaleId + separator + moreExpensiveSaleValue;
    }

    @Override
    public void receiveValues(String[] line) {
        moreExpensiveSaleId = line[SALE_ID_POSITION];
        moreExpensiveSaleValue = new BigDecimal(line[SALE_VALUE_POSITION]);
    }

    @Override
    protected Class<?> dataType() {
        return Sale.class;
    }

    @Override
    protected void processEntry(DataType entry) {
        Sale sale = (Sale) entry;

        if (moreExpensiveSaleValue == null || sale.getSaleValue().compareTo(moreExpensiveSaleValue) > 0) {
            moreExpensiveSaleId = sale.getId().toString();
            moreExpensiveSaleValue = sale.getSaleValue();
        }
    }

}
