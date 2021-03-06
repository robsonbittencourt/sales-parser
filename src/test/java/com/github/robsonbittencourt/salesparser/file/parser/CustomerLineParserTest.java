package com.github.robsonbittencourt.salesparser.file.parser;

import com.github.robsonbittencourt.salesparser.domain.Customer;
import com.github.robsonbittencourt.salesparser.file.parser.CustomerLineParser;
import com.github.robsonbittencourt.salesparser.file.parser.LineParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerLineParserTest {

    @Test
    public void shouldParseLineWhenContainsSalesmanEntry() {
        String lineEntry = "002ç2345675434544345çJose da SilvaçRural";

        LineParser parser = new CustomerLineParser();
        Customer salesman = (Customer)parser.parseLine(lineEntry);

        assertEquals("2345675434544345", salesman.getCnpj());
        assertEquals("Jose da Silva", salesman.getName());
        assertEquals("Rural", salesman.getBusinessArea());
    }

}