package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.DataType;

public abstract class SalesReportItem {

    protected String value;

    public abstract String description();
    public abstract String value();
    public abstract String allValues();
    public abstract void receiveValues(String[] values);

    protected abstract Class<?> dataType();
    protected abstract void processEntry(DataType entry);

    public void process(DataType entry) {
        if (dataType().equals(entry.getClass())) {
            processEntry(entry);
        }
    }

}
