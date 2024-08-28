package com.customer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.beans.CustomerBean;
import com.customer.entity.Customer;
import com.customer.entity.Order;
import com.customer.exception.OrderNotFoundException;
import com.customer.repository.CustomerRepository;
import com.customer.repository.OrderRepository;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Customer registerCustomer(CustomerBean customerBean) {
        logger.info("Registering customer with details: {}", customerBean);
        Customer customer = new Customer();
        customer.setAddress(customerBean.getAddress());
        customer.setEmail(customerBean.getEmail());
        customer.setName(customerBean.getName());
        customer.setPhNumber(customerBean.getPhNumber());

        Customer registeredCustomer = customerRepository.save(customer);
        logger.info("Customer registered with ID: {}", registeredCustomer.getId());
        return registeredCustomer;
    }

    public List<Order> getOrdersByCustomerId(Long customerId) throws OrderNotFoundException {
        logger.info("Fetching orders for customer ID: {}", customerId);
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            logger.warn("No orders found for customer ID: {}", customerId);
            throw new OrderNotFoundException("Orders not found for customer with ID: " + customerId);
        }
        logger.info("Orders found for customer ID: {} - Count: {}", customerId, orders.size());
        
        return orders;
    }
}























