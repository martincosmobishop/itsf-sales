package com.dslobozian.itsf_sales.portal.web.controller;

import com.dslobozian.itsf_sales.billing.receipt.ReceiptDetails;
import com.dslobozian.itsf_sales.billing.receipt.service.ReceiptService;
import com.dslobozian.itsf_sales.sales.basket.Basket;
import com.dslobozian.itsf_sales.sales.order.Order;
import com.dslobozian.itsf_sales.sales.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ReceiptService receiptService;

    public OrderController(OrderService orderService, ReceiptService receiptService) {
        this.orderService = orderService;
        this.receiptService = receiptService;
    }

    @PostMapping
    public ReceiptDetails placeOrder(@RequestBody @Valid Basket basket) {
        Order order = orderService.placeOrder(basket);
        return receiptService.createReceipt(order);
    }

}
