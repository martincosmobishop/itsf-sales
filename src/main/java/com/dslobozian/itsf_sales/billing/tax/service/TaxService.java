package com.dslobozian.itsf_sales.billing.tax.service;

import com.dslobozian.itsf_sales.sales.catalog.Product;
import com.dslobozian.itsf_sales.sales.order.Order;

import java.math.BigDecimal;

public interface TaxService {

    BigDecimal computeTax(Order order);

    BigDecimal computeTax(Product product, Integer quantity);
}
