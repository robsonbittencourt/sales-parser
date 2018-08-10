package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.domain.Salesman;

public class SalesmanQuantity extends ReportItem {

    private Long salesmanQuantity = 0l;

    @Override
    public String description() {
        return "Quantidade de vendedores no arquivo de entrada";
    }

    @Override
    public String value() {
        return Long.toString(salesmanQuantity);
    }

    @Override
    protected Class<?> dataType() {
        return Salesman.class;
    }

    @Override
    public void processEntry(DataType entry) {
        if (entry != null) {
            salesmanQuantity++;
        }
    }

}
