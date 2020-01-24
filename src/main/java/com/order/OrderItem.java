package com.order;

import com.item.Item;
import com.order.types.OrderItemId;

import javax.persistence.*;

@Entity
@IdClass(OrderItemId.class)
public class OrderItem {
    private Integer item;
    private Integer order;
    private Integer amount;

    @Id
    private Integer itemId;

    @Id
    private Integer orderId;

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}