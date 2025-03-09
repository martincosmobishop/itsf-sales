package com.dslobozian.itsf_sales.billing.receipt.service;

import com.dslobozian.itsf_sales.billing.receipt.ReceiptDetails;
import com.dslobozian.itsf_sales.sales.order.Order;

import java.math.BigDecimal;

public interface ReceiptService {

    ReceiptDetails createReceipt(Order order);

    BigDecimal computeOrderTotalAmount(Order order);
}
