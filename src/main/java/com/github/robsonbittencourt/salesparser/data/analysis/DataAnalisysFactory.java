package com.github.robsonbittencourt.salesparser.data.analysis;

import com.github.robsonbittencourt.salesparser.data.analysis.sales.SalesReportFactory;

import java.util.ArrayList;
import java.util.List;

public class DataAnalisysFactory {

    public static List<DataAnalisys> get() {
        List<DataAnalisys> dataAnalisys = new ArrayList<>();

        dataAnalisys.add(SalesReportFactory.get());

        return dataAnalisys;
    }

}
