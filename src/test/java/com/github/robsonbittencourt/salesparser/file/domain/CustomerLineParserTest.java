package com.github.robsonbittencourt.salesparser.file.domain;

import com.github.robsonbittencourt.salesparser.exception.InvalidLineEntryException;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void shouldThrowExceptionWhenLineDoesNotContainsAllFields() {
        String lineEntry = "002ç2345675434544345çJose da Silva";

        LineParser parser = new CustomerLineParser();

        try {
            Customer customer = (Customer)parser.parseLine(lineEntry);
        } catch (InvalidLineEntryException e) {
            assertEquals("The line doesn't match with customer information", e.getMessage());
            return;
        }

        fail("Expected exception was not thrown");
    }

    @Test
    public void shouldThrowExceptionWhenLineContainsExtraFields() {
        String lineEntry = "001ç3245678865434çPauloç40000.99çAnotherField";

        LineParser parser = new CustomerLineParser();

        try {
            Customer customer = (Customer)parser.parseLine(lineEntry);
        } catch (InvalidLineEntryException e) {
            assertEquals("The line doesn't match with customer information", e.getMessage());
            return;
        }

        fail("Expected exception was not thrown");
    }

}