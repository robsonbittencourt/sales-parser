package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.domain.Salesman;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class SalesmanQuantity extends SalesReportItem {

    private static final int CPF_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int SALARY_POSITION = 2;

    @Value("${consolidated.data.separator}")
    private String separator;

    private Set<Salesman> sellers = new HashSet<>();

    @Override
    public String description() {
        return "Quantidade de vendedores no arquivo de entrada";
    }

    @Override
    public String value() {
        return Integer.toString(sellers.size());
    }

    @Override
    public String allValues() {
        StringBuilder sb = new StringBuilder();

        sellers.stream().forEach(c -> {
            sb.append(c.getCpf()).append(separator).append(c.getName()).append(separator).append(c.getSalary()).append(System.lineSeparator());
        });

        return sb.toString();
    }

    @Override
    public void receiveValues(String[] values) {
        String cpf = values[CPF_POSITION];
        String name = values[NAME_POSITION];
        BigDecimal salary = new BigDecimal(values[SALARY_POSITION]);

        sellers.add(new Salesman(cpf, name, salary));
    }

    @Override
    protected Class<?> dataType() {
        return Salesman.class;
    }

    @Override
    public void processEntry(DataType entry) {
        Salesman salesman = (Salesman) entry;

        sellers.add(salesman);
    }

}
