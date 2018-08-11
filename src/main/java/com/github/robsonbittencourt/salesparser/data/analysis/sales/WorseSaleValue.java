package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.domain.Sale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WorseSaleValue extends SalesReportItem {

    private static final int SALESMAN_NAME_POSITION = 0;
    private static final int TOTAL_SALES_VALUE_POSITION = 1;

    @Value("${consolidated.data.separator}")
    private String separator;

    private Map<String, BigDecimal> salesBySalesman = new ConcurrentHashMap<>();

    @Override
    public String allValues() {
        StringBuilder sb = new StringBuilder();

        salesBySalesman.forEach((salesmanName, salesValue) -> sb.append(salesmanName).append(separator).append(salesValue).append(System.lineSeparator()));

        return sb.toString();
    }

    @Override
    public void receiveValues(String[] values) {
        String salesmanName = values[SALESMAN_NAME_POSITION];
        BigDecimal totalSalesValue = new BigDecimal(values[TOTAL_SALES_VALUE_POSITION]);

        salesBySalesman.put(salesmanName, totalSalesValue);
    }

    @Override
    public String description() {
        return "O pior vendedor";
    }

    @Override
    public String value() {
        if (salesBySalesman.size() == 0) {
            return "0";
        }

        Entry<String, BigDecimal> worseSalesmanEntry = Collections.min(salesBySalesman.entrySet(), Comparator.comparing(Entry::getValue));

        return worseSalesmanEntry.getKey();
    }

    @Override
    protected Class<?> dataType() {
        return Sale.class;
    }

    @Override
    protected void processEntry(DataType entry) {
        Sale sale = (Sale) entry;

        BigDecimal salesValue = salesBySalesman.get(sale.getSalesmanName());

        if (salesValue == null) {
            salesBySalesman.put(sale.getSalesmanName(), sale.getSaleValue());
        } else {
            salesBySalesman.put(sale.getSalesmanName(), sale.getSaleValue().add(salesValue));
        }
    }

}
