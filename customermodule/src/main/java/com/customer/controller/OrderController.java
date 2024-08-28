package com.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.customer.beans.OrderBean;
import com.customer.entity.Order;
import com.customer.exception.OrderNotFoundException;
import com.customer.service.OrderService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody OrderBean orderBean) {
        logger.info("Received request to place order: {}", orderBean);
        Order order;
        try {
            order = orderService.placeOrder(orderBean);
            logger.info("Order placed successfully with ID: {}", order.getId());
        } catch (Exception e) {
            logger.error("Failed to place order: {}", orderBean, e);
            throw e;  
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        logger.info("Received request to cancel order with ID: {}", orderId);
        Order order;
        try {
            order = orderService.cancelOrder(orderId);
            logger.info("Order canceled successfully with ID: {}", orderId);
        } catch (OrderNotFoundException e) {
            logger.error("Failed to cancel order with ID: {}", orderId, e);
            throw e; 
        }
        return ResponseEntity.ok(order);
    }
}

