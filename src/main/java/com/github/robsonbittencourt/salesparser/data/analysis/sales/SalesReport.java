package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisys;
import com.github.robsonbittencourt.salesparser.domain.DataType;

import java.util.List;

public class SalesReport implements DataAnalisys {

    private List<ReportItem> reportItems;

    public SalesReport(List<ReportItem> reportItems) {
        this.reportItems = reportItems;
    }

    @Override
    public void processEntry(DataType entry) {
        reportItems.stream().forEach(r -> r.process(entry));
    }

    @Override
    public void allEntriesProcessed() {
        for (ReportItem reportItem : reportItems) {
            System.out.println(reportItem.description() + ": " + reportItem.value());
        }
    }

}
