package com.dslobozian.itsf_sales.sales.order;

import com.dslobozian.itsf_sales.sales.catalog.Product;
import jakarta.validation.constraints.NotEmpty;

import java.util.Map;

/**
 * The product prices normally can vary.
 * So an order should have the prices at the shopping time.
 * That would imply managing the history of the prices (an example would be a table with productId, price, startDate, endDate).
 * In here I simplified by putting directly the products in the Order
 * */
public class Order {
    @NotEmpty
    Map<Product, Integer> products;

    public Order() {
    }

    public Order(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}
