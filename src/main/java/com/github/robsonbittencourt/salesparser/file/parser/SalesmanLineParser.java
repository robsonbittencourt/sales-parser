package com.github.robsonbittencourt.salesparser.file.parser;

import com.github.robsonbittencourt.salesparser.domain.Salesman;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
final class SalesmanLineParser extends AbstractLineParser {

    public static final int CPF_POSITION = 1;
    public static final int NAME_POSITION = 2;
    public static final int SALARY_POSITION = 3;

    @Override
    public Salesman parseLine(String line) {
        String cpf = getString(line, CPF_POSITION);
        String name = getString(line, NAME_POSITION);
        BigDecimal salary = getBigDecimal(line, SALARY_POSITION);

        return new Salesman(cpf, name, salary);
    }

    @Override
    protected String separator() {
        return "ç";
    }

    @Override
    protected int fieldsQuantity() {
        return 4;
    }

}
