package com.github.robsonbittencourt.salesparser.data.analysis;

import com.github.robsonbittencourt.salesparser.data.analysis.sales.SalesReport;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DataAnalisysFactoryTest {

    @Test
    public void shouldReturnDataAnalisysListWithCorrectAnalisys() {
        List<DataAnalisys> dataAnalisys = DataAnalisysFactory.get();

        assertEquals(1, dataAnalisys.size());
        assertEquals(SalesReport.class, dataAnalisys.get(0).getClass());
    }

}