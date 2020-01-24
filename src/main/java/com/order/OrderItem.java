package com.order;

import com.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @EmbeddedId
    private OrderItemKey id;


    @ManyToOne
    @MapsId("item_id")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer amount;


    public Order getOrderId() {
        return order;
    }

    public void setOrderId(Order orderId) {
        this.order = orderId;
    }

    public Item getItemId() {
        return item;
    }

    public void setItemId(Item itemId) {
        this.item = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public OrderItemKey getId() {
        return id;
    }

    public void setId(OrderItemKey id) {
        this.id = id;
    }
}
