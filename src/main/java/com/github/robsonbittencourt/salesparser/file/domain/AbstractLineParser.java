package com.github.robsonbittencourt.salesparser.file.domain;

import com.github.robsonbittencourt.salesparser.exception.InvalidLineEntryException;

import java.math.BigDecimal;

public abstract class AbstractLineParser implements LineParser {

    protected abstract String separator();

    protected abstract int fieldsQuantity();

    public String getString(String line, int position) {
        return getField(line, position);
    }

    public Integer getInteger(String line, int position) {
        String field = getField(line, position);

        try {
            return new Integer(field);
        } catch (NumberFormatException e) {
            throw new InvalidLineEntryException("Invalid entry. Expected Integer, found: " + field);
        }
    }

    public Long getLong(String line, int position) {
        String field = getField(line, position);

        try {
            return new Long(field);
        } catch (NumberFormatException e) {
            throw new InvalidLineEntryException("Invalid entry. Expected Long, found: " + field);
        }
    }

    public BigDecimal getBigDecimal(String line, int position) {
        String field = getField(line, position);

        try {
            return new BigDecimal(field);
        } catch (NumberFormatException e) {
            throw new InvalidLineEntryException("Invalid entry. Expected BigDecimal, found: " + field);
        }
    }

    private String getField(String line, int position) {
        String[] fields = line.split(separator());

        if (fields.length != fieldsQuantity()) {
            throw new InvalidLineEntryException("The line doesn't match with data type");
        }

        return fields[position];
    }

}
