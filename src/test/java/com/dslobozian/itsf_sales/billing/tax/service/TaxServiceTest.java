package com.dslobozian.itsf_sales.billing.tax.service;

import com.dslobozian.itsf_sales.sales.catalog.Product;
import com.dslobozian.itsf_sales.sales.catalog.ProductType;
import com.dslobozian.itsf_sales.sales.order.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaxServiceTest {

    @Autowired
    private TaxService taxService;

    @Test
    public void givenAnOrderWithNonImportedExemptedProduct_whenTaxCalculate_thenTaxIsZero() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("book", BigDecimal.valueOf(12.32), ProductType.BOOK, false), 1);
        Order order = new Order(products);
        BigDecimal calculatedTax = taxService.computeTax(order);
        assertEquals(0, BigDecimal.ZERO.compareTo(calculatedTax));
    }

    @Test
    public void givenAnOrderWithImportedExemptedProduct_whenTaxCalculate_thenTaxIsFive() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("book", BigDecimal.valueOf(12.32), ProductType.BOOK, true), 1);
        Order order = new Order(products);
        BigDecimal calculatedTax = taxService.computeTax(order);
        assertEquals(0 , BigDecimal.valueOf(0.60).compareTo(calculatedTax));
    }

    @Test
    public void givenAnOrderWithNonImportedNonExemptedProduct_whenTaxCalculate_thenTaxIsTen() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("bicycle", BigDecimal.valueOf(12.32), ProductType.OTHER, false), 1);
        Order order = new Order(products);
        BigDecimal calculatedTax = taxService.computeTax(order);
        assertEquals(0, BigDecimal.valueOf(1.15).compareTo(calculatedTax));
    }

    @Test
    public void givenAnOrderWithImportedNonExemptedProduct_whenTaxCalculate_thenTaxIsFifteen() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("bicycle", BigDecimal.valueOf(12.32), ProductType.OTHER, true), 1);
        Order order = new Order(products);
        BigDecimal calculatedTax = taxService.computeTax(order);
        assertEquals(0, BigDecimal.valueOf(1.60).compareTo(calculatedTax));
    }
}
