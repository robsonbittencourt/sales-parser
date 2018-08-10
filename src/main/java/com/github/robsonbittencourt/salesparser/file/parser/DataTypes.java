package com.github.robsonbittencourt.salesparser.file.parser;

import com.github.robsonbittencourt.salesparser.domain.Customer;
import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.domain.Sale;
import com.github.robsonbittencourt.salesparser.domain.Salesman;
import lombok.Getter;

@Getter
public enum DataTypes {

    SALESMAN("001", Salesman.class, new SalesmanLineParser()),
    CUSTOMER("002", Customer.class, new CustomerLineParser()),
    SALE("003", Sale.class, new SaleParser());

    private String id;
    private Class<? extends DataType> dataType;
    private LineParser lineParser;

    DataTypes(String id, Class<? extends DataType> dataType, LineParser lineParser) {
        this.id = id;
        this.dataType = dataType;
        this.lineParser = lineParser;
    }

    public static DataTypes getByCode(String id) {
        for (DataTypes dataTypes : values()) {
            if (dataTypes.getId().equals(id)) {
                return dataTypes;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + id);
    }

    public DataType parseLine(String entry) {
        return lineParser.parseLine(entry);
    }

}
