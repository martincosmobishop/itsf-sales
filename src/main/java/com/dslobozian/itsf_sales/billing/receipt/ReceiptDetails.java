package com.dslobozian.itsf_sales.billing.receipt;

import com.dslobozian.itsf_sales.sales.order.Order;

import java.math.BigDecimal;

public class ReceiptDetails {

    private Order order;
    private BigDecimal tax;
    private BigDecimal totalAmount;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
