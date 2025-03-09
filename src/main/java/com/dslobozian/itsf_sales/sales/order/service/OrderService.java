package com.dslobozian.itsf_sales.sales.order.service;

import com.dslobozian.itsf_sales.sales.basket.Basket;
import com.dslobozian.itsf_sales.sales.order.Order;

public interface OrderService {

    Order placeOrder(Basket basket);
}
