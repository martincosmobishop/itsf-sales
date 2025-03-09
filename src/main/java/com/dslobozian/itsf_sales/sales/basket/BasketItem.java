package com.dslobozian.itsf_sales.sales.basket;

import com.dslobozian.itsf_sales.sales.catalog.ProductType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * Normally in a basket item we should have a productId and a quantity.
 * The rest of the data (title, price, productType, isImported) should be retrieved
 * from {@link com.dslobozian.itsf_sales.sales.catalog.Product}. In here I just simplified
 * */
public record BasketItem(@NotEmpty String productTitle,
                         @DecimalMin(value = "0.01")  @Digits(integer = 8, fraction = 2) BigDecimal price,
                         @NotNull ProductType productType,
                         boolean isImported,
                         @Min(1) int quantity) {
}
