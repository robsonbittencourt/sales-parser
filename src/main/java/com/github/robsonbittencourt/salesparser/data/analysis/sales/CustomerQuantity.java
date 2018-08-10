package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.Customer;
import com.github.robsonbittencourt.salesparser.domain.DataType;

public class CustomerQuantity extends ReportItem {

    private Long customersQuantity = 0l;

    @Override
    public String description() {
        return "Quantidade de clientes no arquivo de entrada";
    }

    @Override
    public String value() {
        return Long.toString(customersQuantity);
    }

    @Override
    protected Class<?> dataType() {
        return Customer.class;
    }

    @Override
    public void processEntry(DataType entry) {
        if (entry != null) {
            customersQuantity++;
        }
    }

}
