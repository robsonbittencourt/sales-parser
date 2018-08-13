package com.github.robsonbittencourt.salesparser.file.parser;

import com.github.robsonbittencourt.salesparser.domain.Salesman;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SalesmanLineParserTest {

    @Test
    public void shouldParseLineWhenContainsSalesmanEntry() {
        String lineEntry = "001ç3245678865434çPauloç40000.99";

        LineParser parser = new SalesmanLineParser();
        Salesman salesman = (Salesman)parser.parseLine(lineEntry);

        assertEquals("3245678865434", salesman.getCpf());
        assertEquals("Paulo", salesman.getName());
        assertEquals("40000.99", salesman.getSalary().toString());
    }

}