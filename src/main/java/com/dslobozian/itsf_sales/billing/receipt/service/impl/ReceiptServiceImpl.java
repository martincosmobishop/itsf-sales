package com.dslobozian.itsf_sales.billing.receipt.service.impl;

import com.dslobozian.itsf_sales.billing.receipt.ReceiptDetails;
import com.dslobozian.itsf_sales.billing.receipt.service.ReceiptService;
import com.dslobozian.itsf_sales.billing.tax.service.TaxService;
import com.dslobozian.itsf_sales.sales.order.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final TaxService taxService;

    public ReceiptServiceImpl(TaxService taxService) {
        this.taxService = taxService;
    }

    @Override
    public ReceiptDetails createReceipt(Order order) {
        ReceiptDetails details = new ReceiptDetails();
        details.setOrder(order);
        details.setTax(taxService.computeTax(order));
        details.setTotalAmount(computeOrderTotalAmount(order));
        return details;
    }


    @Override
    public BigDecimal computeOrderTotalAmount(Order order) {
        return order.getProducts().entrySet().stream()
            .map(e -> e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue())))
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .setScale(2, RoundingMode.FLOOR);
    }
}
