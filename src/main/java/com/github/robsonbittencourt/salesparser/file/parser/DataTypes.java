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

    private String code;
    private Class<? extends DataType> dataType;
    private LineParser lineParser;

    DataTypes(String code, Class<? extends DataType> dataType, LineParser lineParser) {
        this.code = code;
        this.dataType = dataType;
        this.lineParser = lineParser;
    }

    public static DataTypes getByCode(String code) {
        for (DataTypes dataTypes : values()) {
            if (dataTypes.getCode().equals(code)) {
                return dataTypes;
            }
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public DataType parseLine(String entry) {
        return lineParser.parseLine(entry);
    }

}
