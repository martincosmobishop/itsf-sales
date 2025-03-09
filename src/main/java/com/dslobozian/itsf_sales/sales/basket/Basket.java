package com.dslobozian.itsf_sales.sales.basket;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * In a basket we should have a basketId.
 * */
public class Basket {

    // In here we should add a basketId

    @NotEmpty
    List<BasketItem> basketItems;

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }
}
