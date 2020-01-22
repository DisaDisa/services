package com.payment.dto;

import com.payment.types.PaymentStatus;

public class UserDetailsDto {
    private String username;
    private PaymentStatus cardAuthorizationInfo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PaymentStatus getCardAuthorizationInfo() {
        return cardAuthorizationInfo;
    }

    public void setCardAuthorizationInfo(PaymentStatus cardAuthorizationInfo) {
        this.cardAuthorizationInfo = cardAuthorizationInfo;
    }
}
