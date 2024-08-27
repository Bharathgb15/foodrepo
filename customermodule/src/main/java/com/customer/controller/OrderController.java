package com.customer.controller;

import com.customer.beans.OrderBean;
import com.customer.entity.Order;
import com.customer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder (@RequestBody OrderBean orderBean)
    		{
    			
        Order order = orderService.placeOrder(orderBean);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/cancel/{orderId}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        Order order =orderService.cancelOrder(orderId);
        return ResponseEntity.ok(order);
    }
}
