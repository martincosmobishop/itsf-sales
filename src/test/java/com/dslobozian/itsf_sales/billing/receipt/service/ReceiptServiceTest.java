package com.dslobozian.itsf_sales.billing.receipt.service;

import com.dslobozian.itsf_sales.billing.receipt.ReceiptDetails;
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
public class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;


    @Test
    public void givenAnOrderWithAllKindsOfProducts_whenTaxCalculate_thenTaxIsOk() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("bottle of perfume", BigDecimal.valueOf(32.19), ProductType.OTHER, true), 1);
        products.put(new Product("bottle of perfume", BigDecimal.valueOf(20.89), ProductType.OTHER, false), 1);
        products.put(new Product("packet of headache pills", BigDecimal.valueOf(9.75), ProductType.MEDICAL_EQUIPMENT, false), 1);
        products.put(new Product("box of chocolates", BigDecimal.valueOf(11.85), ProductType.FOOD, true), 1);
        ReceiptDetails receiptDetails = receiptService.createReceipt(new Order(products));
        assertEquals(0, BigDecimal.valueOf(6.70).compareTo(receiptDetails.getTax()));
        assertEquals(0, BigDecimal.valueOf(74.68).compareTo(receiptDetails.getTotalAmount()));
    }

    @Test
    public void givenAnOrderWithOnlyImportedProducts_whenTaxCalculate_thenTaxIsOk() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("box of chocolates", BigDecimal.valueOf(10.50), ProductType.FOOD, true), 1);
        products.put(new Product("bottle of perfume", BigDecimal.valueOf(54.65), ProductType.OTHER, true), 1);
        ReceiptDetails receiptDetails = receiptService.createReceipt(new Order(products));
        assertEquals(0, BigDecimal.valueOf(7.65).compareTo(receiptDetails.getTax()));
        assertEquals(0, BigDecimal.valueOf(65.15).compareTo(receiptDetails.getTotalAmount()));
    }

    @Test
    public void givenAnOrderWithOnlyNonImportedProducts_whenTaxCalculate_thenTaxIsOk() {
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product("book", BigDecimal.valueOf(12.49), ProductType.BOOK, false), 1);
        products.put(new Product("music CD", BigDecimal.valueOf(16.49), ProductType.OTHER, false), 1);
        products.put(new Product("chocolate bar", BigDecimal.valueOf(0.85), ProductType.FOOD, false), 1);
        ReceiptDetails receiptDetails = receiptService.createReceipt(new Order(products));
        assertEquals(0, BigDecimal.valueOf(1.50).compareTo(receiptDetails.getTax()));
        assertEquals(0, BigDecimal.valueOf(29.83).compareTo(receiptDetails.getTotalAmount()));
    }

}
