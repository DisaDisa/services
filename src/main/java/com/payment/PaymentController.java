package com.payment;


import com.payment.dto.UserDetailsDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final Payments paymentsController;

    public PaymentController(Payments paymentsController) {
        this.paymentsController = paymentsController;
    }


    @PutMapping("api/orders/{order_id}/payment")
    public void performPayment(@PathVariable Integer order_id, @RequestBody UserDetailsDto userDetailsDto) {
        paymentsController.performPayment(order_id, userDetailsDto);
    }
}