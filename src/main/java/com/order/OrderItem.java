package com.order;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //TODO: FIX FOREIGN KEY
    /*
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Integer orderId;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Integer itemId;
     */
}
