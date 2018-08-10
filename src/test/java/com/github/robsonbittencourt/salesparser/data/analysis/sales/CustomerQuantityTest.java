package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerQuantityTest {

    @Test
    public void shouldReturnCorrectDescription() {
        String description = new CustomerQuantity().description();

        assertEquals("Quantidade de clientes no arquivo de entrada", description);
    }
    
    @Test
    public void shouldReturnCorrectDataType() {
        Class<?> clazz = new CustomerQuantity().dataType();

        assertEquals(Customer.class, clazz);
    }

    @Test
    public void shouldCountCustomerWhenProcess() {
        Customer c1 = new Customer("123", "Carlos", "Tech");
        Customer c2 = new Customer("456", "Charlie", "Medicine");
        Customer c3 = new Customer("789", "Mike", "Business");

        ReportItem reportItem = new CustomerQuantity();
        reportItem.processEntry(c1);
        reportItem.processEntry(c2);
        reportItem.processEntry(c3);

        assertEquals("3", reportItem.value());
    }

}