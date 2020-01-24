package com.order;

import com.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private OrderItem id;
    private Integer item;
    private Integer order;
    private Integer amount;

    @OneToOne
    @JoinColumn(name="item_id")
    private Item item_id;

    @OneToOne
    @JoinColumn(name="order_id")
    private Order order_id;

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