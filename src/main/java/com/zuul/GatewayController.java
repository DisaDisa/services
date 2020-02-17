package com.zuul;

import com.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

import java.util.ArrayList;
import java.util.List;


@RestController
public class GatewayController {
    @Autowired
    private EurekaClient eurekaClient;


    private List <Item> getItems() {
        //eurekaClient.getNextServerFromEureka();
        ArrayList<Item> list = new ArrayList<>();
        return list;
    }

}