package com.example.demodeploy;

import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController("/")
@Log


public class MyController{
    
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

        public MyController(){
            this.orders= new ArrayList<>();
        }

        private ArrayList<Order> orders;



        @PostMapping(value = "/order",consumes = MediaType.APPLICATION_JSON_VALUE)
        public void send(@RequestBody Order order) {

            // Return an 202 - Accepted response.
            log.info("request received: " + order.toString());

            sendOrder(order);

        }

        @GetMapping("/order")
        public OrderAggregate getOrders(){
            log.info("Getting orders");
            return new OrderAggregate(orders);
        }

        public void sendOrder(Order order){
            log.info("Order received");

            //manage wheel and tyre tipes

            switch (order.type) {
                case  "wheel" :

                    log.info("wheel received");
                    orders.add(order);
                    break;
                case  "tyre" :

                    log.info("tyre received");
                    orders.add(order);
                    break;
                default:

                    log.info("unsopported item");
                    throw new RuntimeException("Unknow");
            }
        }
}