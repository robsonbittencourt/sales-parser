package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisys;

import java.util.ArrayList;
import java.util.List;

public class SalesReportFactory {

    public static DataAnalisys get() {
        List<ReportItem> reportItems = new ArrayList<>();

        reportItems.add(new CustomerQuantity());
        reportItems.add(new SalesmanQuantity());
        reportItems.add(new MoreExpensiveSale());
        reportItems.add(new WorseSalesman());

        return new SalesReport(reportItems);
    }

}
