package com.github.robsonbittencourt.salesparser.file.parser;

import com.github.robsonbittencourt.salesparser.domain.SaleItem;
import com.github.robsonbittencourt.salesparser.file.parser.LineParser;
import com.github.robsonbittencourt.salesparser.file.parser.SaleItemParser;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SaleItemParserTest {

    @Test
    public void shouldParseLineWhenContainsSaleItemEntry() {
        String lineEntry = "3-40-3.10";

        LineParser parser = new SaleItemParser();
        SaleItem saleItemEntry = (SaleItem)parser.parseLine(lineEntry);

        assertEquals(new Long(3), saleItemEntry.getId());
        assertEquals(40, saleItemEntry.getQuantity());
        assertEquals(new BigDecimal("3.10"), saleItemEntry.getPrice());
    }

}