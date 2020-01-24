package com.payment;


import com.item.Item;
import com.order.types.OrderStatus;
import com.payment.dto.OrderStatusUpdateDto;
import com.payment.dto.UserDetailsDto;
import com.payment.repository.PaymentRepository;
import com.payment.types.PaymentStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Payments {
    private final PaymentRepository paymentRepository;


    @Autowired
    AmqpTemplate template;


    public Payments(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void performPayment(Integer orderId, UserDetailsDto userDetailsDto) {
        Payment payment = new Payment(orderId, userDetailsDto.getUsername(), userDetailsDto.getCardAuthorizationInfo());
        OrderStatus orderStatus;
        if (payment.getPaymentStatus() == PaymentStatus.AUTHORIZED) {
            orderStatus = OrderStatus.PAID;
        } else {
            orderStatus = OrderStatus.FAILED;
        }
        OrderStatusUpdateDto orderStatusUpdateDto = new OrderStatusUpdateDto();
        orderStatusUpdateDto.setOrderId(orderId);
        orderStatusUpdateDto.setNewStatus(orderStatus);
        template.convertAndSend("order-status-update", orderStatusUpdateDto);

    }
}
