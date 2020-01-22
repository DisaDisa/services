package com.order;

import com.order.dto.ItemAdditionalParametrsDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final Orders ordersController;

    public OrderController(Orders ordersController){
        this.ordersController = ordersController;
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getOrders(){
        return ordersController.getOrders();
    }

    @RequestMapping(value = "/api/orders/{order_id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrderById(@PathVariable Integer order_id){
        return ordersController.getOrderById(order_id);
    }

    @RequestMapping(value = "/api/orders/{order_id}/item", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addItemToOrder(@PathVariable Integer order_id, @RequestBody ItemAdditionalParametrsDto itemDto){
        ordersController.addItemToOrder(order_id, itemDto);
    }

    @RequestMapping(value = "/api/orders/{order_id}/status/{status}/", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeOrderStatus(@PathVariable Integer order_id,@PathVariable Integer status){
        ordersController.changeOrderStatus(order_id, status);
    }

}
