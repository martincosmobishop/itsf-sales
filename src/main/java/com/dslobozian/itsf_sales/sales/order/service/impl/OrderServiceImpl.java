package com.dslobozian.itsf_sales.sales.order.service.impl;

import com.dslobozian.itsf_sales.sales.basket.Basket;
import com.dslobozian.itsf_sales.sales.basket.BasketItem;
import com.dslobozian.itsf_sales.sales.catalog.Product;
import com.dslobozian.itsf_sales.sales.order.Order;
import com.dslobozian.itsf_sales.sales.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This is just a converter of Basket to Order.
 * Normally we would have :
 *        - a check of stock in the warehouse (a dedicated domain like warehouse)
 *        - a check of the payment (could be in billing)
 *        - saving the order
 *        - and then returning it
 * */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order placeOrder(Basket basket) {
        Map<Product, Integer> products = new HashMap<>();

        for (BasketItem basketItem : basket.getBasketItems()) {
            products.put(new Product(basketItem.productTitle(), basketItem.price(), basketItem.productType(), basketItem.isImported()), basketItem.quantity());
        }

        return new Order(products);
    }
}
