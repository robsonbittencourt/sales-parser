package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.Customer;
import com.github.robsonbittencourt.salesparser.domain.DataType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerQuantity extends SalesReportItem {

    private static final int CNPJ_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int BUSINESS_AREA_POSITION = 2;

    @Value("${consolidated.data.separator}")
    private String separator;

    private Set<Customer> customers = new HashSet<>();

    @Override
    public String description() {
        return "Quantidade de clientes no arquivo de entrada";
    }

    @Override
    public String value() {
        return Integer.toString(customers.size());
    }

    @Override
    public String allValues() {
        StringBuilder sb = new StringBuilder();

        customers.stream().forEach(c -> {
            sb.append(c.getCnpj()).append(separator).append(c.getName()).append(separator).append(c.getBusinessArea()).append(System.lineSeparator());
        });

        return sb.toString();
    }

    @Override
    public void receiveValues(String[] values) {
        String cnpj = values[CNPJ_POSITION];
        String name = values[NAME_POSITION];
        String businessArea = values[BUSINESS_AREA_POSITION];

        customers.add(new Customer(cnpj, name, businessArea));
    }

    @Override
    protected Class<?> dataType() {
        return Customer.class;
    }

    @Override
    public void processEntry(DataType entry) {
        Customer customer = (Customer) entry;

        customers.add(customer);
    }

}
