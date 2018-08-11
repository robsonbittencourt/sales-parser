package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.Salesman;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SalesmanQuantityTest {

    @InjectMocks
    private SalesmanQuantity salesmanQuantity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(salesmanQuantity, "separator", ":");
    }

    @Test
    public void shouldReturnCorrectDescription() {
        String description = salesmanQuantity.description();

        assertEquals("Quantidade de vendedores no arquivo de entrada", description);
    }

    @Test
    public void shouldReturnCorrectDataType() {
        Class<?> clazz = salesmanQuantity.dataType();

        assertEquals(Salesman.class, clazz);
    }

    @Test
    public void shouldCountCustomerWhenProcess() {
        Salesman s1 = new Salesman("123", "Maria", new BigDecimal("1000.50"));
        Salesman s2 = new Salesman("456", "Lucy", new BigDecimal("2000.50"));
        Salesman s3 = new Salesman("789", "Andrea", new BigDecimal("3000.50"));

        salesmanQuantity.processEntry(s1);
        salesmanQuantity.processEntry(s2);
        salesmanQuantity.processEntry(s3);

        assertEquals("3", salesmanQuantity.value());
    }

    @Test
    public void shouldCountOnlySalesmamWithDifferentCPFWhenProcess() {
        Salesman s1 = new Salesman("123", "Maria", new BigDecimal("1000.50"));
        Salesman s2 = new Salesman("456", "Lucy", new BigDecimal("2000.50"));
        Salesman s3 = new Salesman("789", "Andrea", new BigDecimal("3000.50"));
        Salesman s4 = new Salesman("456", "Lucy", new BigDecimal("2000.50"));

        salesmanQuantity.processEntry(s1);
        salesmanQuantity.processEntry(s2);
        salesmanQuantity.processEntry(s3);
        salesmanQuantity.processEntry(s4);

        assertEquals("3", salesmanQuantity.value());
    }

    @Test
    public void shouldReturnStringWithAllValues() {
        Salesman s1 = new Salesman("123", "Maria", new BigDecimal("1000.50"));
        Salesman s2 = new Salesman("456", "Lucy", new BigDecimal("2000.50"));
        Salesman s3 = new Salesman("789", "Andrea", new BigDecimal("3000.50"));

        salesmanQuantity.processEntry(s1);
        salesmanQuantity.processEntry(s2);
        salesmanQuantity.processEntry(s3);

        String allValues = salesmanQuantity.allValues();

        assertEquals("123:Maria:1000.50\n" +
                "456:Lucy:2000.50\n" +
                "789:Andrea:3000.50\n", allValues);
    }

    @Test
    public void shouldAddSalesmanUsingLine() {
        String moreExpensiveSaleLine = "3445678865434:Joao:42000.99";

        salesmanQuantity.receiveValues(moreExpensiveSaleLine.split(":"));

        assertEquals("1", salesmanQuantity.value());
    }

}