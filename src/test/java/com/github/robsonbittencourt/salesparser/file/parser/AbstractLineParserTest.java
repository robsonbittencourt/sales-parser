package com.github.robsonbittencourt.salesparser.file.parser;

import com.github.robsonbittencourt.salesparser.exception.InvalidLineEntryException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AbstractLineParserTest {

    @Test
    public void shouldThrowExceptionWhenGetIntegerAndGetNotAInteger() {
        AbstractLineParser parser = new CustomerLineParser();

        try {
            parser.getInteger("001ç1234567891234çPedroç50000", 2);
        } catch (InvalidLineEntryException e) {
            assertEquals("Invalid entry. Expected Integer, found: Pedro", e.getMessage());
            return;
        }

        fail("Should be throw exception");
    }

    @Test
    public void shouldThrowExceptionWhenGetLongAndGetNotALong() {
        AbstractLineParser parser = new CustomerLineParser();

        try {
            parser.getLong("001ç1234567891234çPedroç50000", 2);
        } catch (InvalidLineEntryException e) {
            assertEquals("Invalid entry. Expected Long, found: Pedro", e.getMessage());
            return;
        }

        fail("Should be throw exception");
    }

    @Test
    public void shouldThrowExceptionWhenGetBigDecimalAndGetNotABigDecimal() {
        AbstractLineParser parser = new CustomerLineParser();

        try {
            parser.getBigDecimal("001ç1234567891234çPedroç50000", 2);
        } catch (InvalidLineEntryException e) {
            assertEquals("Invalid entry. Expected BigDecimal, found: Pedro", e.getMessage());
            return;
        }

        fail("Should be throw exception");
    }

    @Test
    public void shouldThrowExceptionWhenSendLineWithMoreInformationThatDataType() {
        AbstractLineParser parser = new CustomerLineParser();

        try {
            parser.getString("001ç1234567891234çPedroç50000çAnotherInfo", 2);
        } catch (InvalidLineEntryException e) {
            assertEquals("The line doesn't match with data type", e.getMessage());
            return;
        }

        fail("Should be throw exception");
    }

}