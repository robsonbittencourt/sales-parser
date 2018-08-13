package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.domain.Sale;
import com.github.robsonbittencourt.salesparser.domain.SaleItem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoreExpensiveSaleTest {

    @InjectMocks
    private MoreExpensiveSale moreExpensiveSale;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(moreExpensiveSale, "separator", ":");
    }

    @Test
    public void shouldReturnCorrectDescription() {
        String description = moreExpensiveSale.description();

        assertEquals("ID da venda mais cara", description);
    }

    @Test
    public void shouldReturnCorrectDataType() {
        Class<?> clazz = moreExpensiveSale.dataType();

        assertEquals(Sale.class, clazz);
    }

    @Test
    public void shouldCountSaleValueWhenProcess() {
        List<SaleItem> items1 = new ArrayList<>();
        items1.add(new SaleItem(1l, 10, new BigDecimal("5.35")));
        items1.add(new SaleItem(2l, 50, new BigDecimal("170.15")));
        items1.add(new SaleItem(3l, 45, new BigDecimal("10.12")));

        List<SaleItem> items2 = new ArrayList<>();
        items2.add(new SaleItem(4l, 33, new BigDecimal("150.33")));
        items2.add(new SaleItem(5l, 20, new BigDecimal("10.40")));
        items2.add(new SaleItem(6l, 2, new BigDecimal("1600.20")));

        List<SaleItem> items3 = new ArrayList<>();
        items3.add(new SaleItem(7l, 4, new BigDecimal("1500.33")));
        items3.add(new SaleItem(8l, 43, new BigDecimal("15.76")));
        items3.add(new SaleItem(9l, 15, new BigDecimal("80.40")));

        Sale sale1 = new Sale(10l, items1, "Maria");
        Sale sale2 = new Sale(20l, items2, "Lucy");
        Sale sale3 = new Sale(30l, items3, "Andrea");

        moreExpensiveSale.processEntry(sale1);
        moreExpensiveSale.processEntry(sale2);
        moreExpensiveSale.processEntry(sale3);

        assertEquals("10", moreExpensiveSale.value());
    }

    @Test
    public void shouldReturnStringWithAllValues() {
        List<SaleItem> items1 = new ArrayList<>();
        items1.add(new SaleItem(1l, 10, new BigDecimal("5.35")));
        items1.add(new SaleItem(2l, 50, new BigDecimal("170.15")));
        items1.add(new SaleItem(3l, 45, new BigDecimal("10.12")));

        List<SaleItem> items2 = new ArrayList<>();
        items2.add(new SaleItem(4l, 33, new BigDecimal("150.33")));
        items2.add(new SaleItem(5l, 20, new BigDecimal("10.40")));
        items2.add(new SaleItem(6l, 2, new BigDecimal("1600.20")));

        List<SaleItem> items3 = new ArrayList<>();
        items3.add(new SaleItem(7l, 4, new BigDecimal("1500.33")));
        items3.add(new SaleItem(8l, 43, new BigDecimal("15.76")));
        items3.add(new SaleItem(9l, 15, new BigDecimal("80.40")));

        Sale sale1 = new Sale(10l, items1, "Maria");
        Sale sale2 = new Sale(20l, items2, "Lucy");
        Sale sale3 = new Sale(30l, items3, "Andrea");

        moreExpensiveSale.processEntry(sale1);
        moreExpensiveSale.processEntry(sale2);
        moreExpensiveSale.processEntry(sale3);

        String allValues = moreExpensiveSale.allValues();

        assertEquals("10:9016.40", allValues);
    }

    @Test
    public void shouldAddMoreExpensiveSaleUsingLine() {
        String moreExpensiveSaleLine = "15:720053.50";

        moreExpensiveSale.receiveValues(moreExpensiveSaleLine.split(":"));

        assertEquals("15", moreExpensiveSale.value());
    }

}