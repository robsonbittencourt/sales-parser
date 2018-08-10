package com.github.robsonbittencourt.salesparser.file.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.github.robsonbittencourt.salesparser.exception.InvalidLineEntryException;
import org.junit.Test;

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

    @Test
    public void shouldThrowExceptionWhenLineDoesNotContainsAllFields() {
        String lineEntry = "001ç3245678865434ç40000.99";

        LineParser parser = new SalesmanLineParser();

        try {
            Salesman salesman = (Salesman)parser.parseLine(lineEntry);
        } catch (InvalidLineEntryException e) {
            assertEquals("The line doesn't match with salesman information", e.getMessage());
            return;
        }

        fail("Expected exception was not thrown");
    }

    @Test
    public void shouldThrowExceptionWhenLineContainsExtraFields() {
        String lineEntry = "001ç3245678865434çPauloç40000.99çAnotherField";

        LineParser parser = new SalesmanLineParser();

        try {
            Salesman salesman = (Salesman)parser.parseLine(lineEntry);
        } catch (InvalidLineEntryException e) {
            assertEquals("The line doesn't match with salesman information", e.getMessage());
            return;
        }

        fail("Expected exception was not thrown");
    }

    @Test
    public void shouldThrowExceptionWhenSalaryIsNotANumber() {
        String lineEntry = "001ç3245678865434çPauloçSalary40000.99";

        LineParser parser = new SalesmanLineParser();

        try {
            Salesman salesman = (Salesman)parser.parseLine(lineEntry);
        } catch (InvalidLineEntryException e) {
            assertEquals("Invalid salary: Salary40000.99", e.getMessage());
            return;
        }

        fail("Expected exception was not thrown");
    }

}