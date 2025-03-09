package com.dslobozian.itsf_sales.sales.catalog;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class Product {
    @NotEmpty
    private String title;
    @DecimalMin(value = "0.01")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;
    @NotNull
    private ProductType type;
    private boolean isImported;

    public Product() {
    }

    public Product(String title, BigDecimal price,  ProductType type, boolean isImported) {
        this.title = title;
        this.price = price;
        this.type = type;
        this.isImported = isImported;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return (isImported ? "imported " : "") + title + ": " + price;
    }
}
