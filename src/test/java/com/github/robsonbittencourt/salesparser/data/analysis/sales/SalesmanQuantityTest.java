package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.Salesman;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SalesmanQuantityTest {

    @Test
    public void shouldReturnCorrectDescription() {
        String description = new SalesmanQuantity().description();

        assertEquals("Quantidade de vendedores no arquivo de entrada", description);
    }

    @Test
    public void shouldReturnCorrectDataType() {
        Class<?> clazz = new SalesmanQuantity().dataType();

        assertEquals(Salesman.class, clazz);
    }

    @Test
    public void shouldCountCustomerWhenProcess() {
        Salesman s1 = new Salesman("123", "Maria", new BigDecimal("1000.50"));
        Salesman s2 = new Salesman("456", "Lucy", new BigDecimal("2000.50"));
        Salesman s3 = new Salesman("789", "Andrea", new BigDecimal("3000.50"));

        ReportItem reportItem = new SalesmanQuantity();
        reportItem.processEntry(s1);
        reportItem.processEntry(s2);
        reportItem.processEntry(s3);

        assertEquals("3", reportItem.value());
    }

}