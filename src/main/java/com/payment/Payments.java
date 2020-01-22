package com.payment;


import com.item.Item;
import com.payment.dto.UserDetailsDto;
import com.payment.repository.PaymentRepository;
import com.payment.types.PaymentStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Payments {
    private final PaymentRepository paymentRepository;
    private final String itemsUrl = "api/warehouse/items/";

    public Payments(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void performPayment(Integer orderId, UserDetailsDto userDetailsDto) {
        //TODO

        Payment payment = new Payment(orderId, userDetailsDto.getUsername(), userDetailsDto.getCardAuthorizationInfo());


    }
}
