package com.order;

import javax.persistence.*;

@Entity
@Table(name = "Order")
public class Order {
    @Ids
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer totalAmount;
    private Double totalCost;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
