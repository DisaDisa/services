package com.payment.dto;

import com.order.types.OrderStatus;

public class OrderStatusUpdateDto {
    private Integer orderId;
    private OrderStatus newStatus;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(OrderStatus newStatus) {
        this.newStatus = newStatus;
    }
}
