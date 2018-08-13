package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

public class CustomerQuantityTest {

    @InjectMocks
    private CustomerQuantity customerQuantity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(customerQuantity, "separator", ":");
    }

    @Test
    public void shouldReturnCorrectDescription() {
        String description = customerQuantity.description();

        assertEquals("Quantidade de clientes no arquivo de entrada", description);
    }
    
    @Test
    public void shouldReturnCorrectDataType() {
        Class<?> clazz = customerQuantity.dataType();

        assertEquals(Customer.class, clazz);
    }

    @Test
    public void shouldCountCustomerWhenProcess() {
        Customer c1 = new Customer("123", "Carlos", "Tech");
        Customer c2 = new Customer("456", "Charlie", "Medicine");
        Customer c3 = new Customer("789", "Mike", "Business");

        customerQuantity.processEntry(c1);
        customerQuantity.processEntry(c2);
        customerQuantity.processEntry(c3);

        assertEquals("3", customerQuantity.value());
    }

    @Test
    public void shouldCountOnlyCustomerWithDifferentCNPJWhenProcess() {
        Customer c1 = new Customer("123", "Carlos", "Tech");
        Customer c2 = new Customer("456", "Charlie", "Medicine");
        Customer c3 = new Customer("789", "Mike", "Business");
        Customer c4 = new Customer("789", "Mike", "Business");

        customerQuantity.processEntry(c1);
        customerQuantity.processEntry(c2);
        customerQuantity.processEntry(c3);
        customerQuantity.processEntry(c4);

        assertEquals("3", customerQuantity.value());
    }

    @Test
    public void shouldReturnStringWithAllValues() {
        Customer c1 = new Customer("123", "Carlos", "Tech");
        Customer c2 = new Customer("456", "Charlie", "Medicine");
        Customer c3 = new Customer("789", "Mike", "Business");

        customerQuantity.processEntry(c1);
        customerQuantity.processEntry(c2);
        customerQuantity.processEntry(c3);

        String allValues = customerQuantity.allValues();

        assertEquals("123:Carlos:Tech\n" +
                "456:Charlie:Medicine\n" +
                "789:Mike:Business\n", allValues);
    }

    @Test
    public void shouldAddCustomerUsingLine() {
        String customerLine = "2345675433444345:Eduardo Pereira:Rural";

        customerQuantity.receiveValues(customerLine.split(":"));

        assertEquals("1", customerQuantity.value());
    }


}