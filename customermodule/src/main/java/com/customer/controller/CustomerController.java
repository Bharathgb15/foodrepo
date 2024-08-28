package com.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.beans.CustomerBean;
import com.customer.entity.Customer;
import com.customer.entity.Order;
import com.customer.exception.OrderNotFoundException;
import com.customer.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody CustomerBean customerBean) {
        logger.info("Received request to register customer: {}", customerBean);
        Customer registeredCustomer = customerService.registerCustomer(customerBean);
        logger.info("Customer registered successfully with ID: {}", registeredCustomer.getId());
        return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Long customerId) {
        logger.info("Received request to fetch orders for customer ID: {}", customerId);
        List<Order> orders;
        try {
            orders = customerService.getOrdersByCustomerId(customerId);
            logger.info("Fetched {} orders for customer ID: {}", orders.size(), customerId);
        } catch (OrderNotFoundException e) {
            logger.error("No orders found for customer ID: {}", customerId, e);
            throw e; 
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}




