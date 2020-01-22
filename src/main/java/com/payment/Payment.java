package com.payment;


import javax.persistence.*;
import com.payment.types.PaymentStatus;

@Entity
@Table(name = "Payment")
public class Payment {


    @Id
    private Integer orderId;
    private String username;
    private PaymentStatus paymentStatus;

    public Payment(Integer orderId, String username, PaymentStatus paymentStatus) {
        this.orderId = orderId;
        this.username = username;
        this.paymentStatus = paymentStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
