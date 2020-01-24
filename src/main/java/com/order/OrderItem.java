package com.order;

import com.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @EmbeddedId
    private OrderItemKey id;
    private Integer item;
    private Integer order;
    private Integer amount;


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