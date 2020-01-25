package com.order;

import com.item.Item;
import com.order.types.OrderItemId;

import javax.persistence.*;

@Entity
@IdClass(OrderItemId.class)
public class OrderItem {
    private Integer amount;

    @Id
    private Integer itemId;

    @Id
    private Integer orderId;



    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}