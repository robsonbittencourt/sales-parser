package com.github.robsonbittencourt.salesparser.domain;

import com.github.robsonbittencourt.salesparser.file.parser.LineParser;
import com.github.robsonbittencourt.salesparser.file.parser.SaleParser;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SaleParserTest {

    @Test
    public void shouldParseLineWhenContainsSaleEntry() {
        String lineEntry = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

        LineParser parser = new SaleParser();
        Sale saleEntry = (Sale)parser.parseLine(lineEntry);

        assertEquals(new Long(10), saleEntry.getId());
        assertEquals("Pedro", saleEntry.getName());

        List<SaleItem> items = saleEntry.getItems();
        assertEquals(3, items.size());

        assertEquals(new Long(1), items.get(0).getId());
        assertEquals(10, items.get(0).getQuantity());
        assertEquals(new BigDecimal(100), items.get(0).getPrice());

        assertEquals(new Long(2), items.get(1).getId());
        assertEquals(30, items.get(1).getQuantity());
        assertEquals(new BigDecimal("2.50"), items.get(1).getPrice());

        assertEquals(new Long(3), items.get(2).getId());
        assertEquals(40, items.get(2).getQuantity());
        assertEquals(new BigDecimal("3.10"), items.get(2).getPrice());
    }

}