package com.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.beans.MenuItem;
import com.customer.beans.OrderBean;
import com.customer.entity.Customer;
import com.customer.entity.Order;
import com.customer.exception.InvalidOrderException;
import com.customer.exception.OrderNotFoundException;
import com.customer.repository.CustomerRepository;
import com.customer.repository.OrderRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Order placeOrder(OrderBean orderBean) {
        logger.info("Attempting to place order with details: {}", orderBean);
        Customer customer = customerRepository.findById(orderBean.getCustomerId())
                .orElseThrow(() -> new OrderNotFoundException("Customer not found with id: " + orderBean.getCustomerId()));

        String menuServiceUrl = "http://localhost:8083/app1/dish/" + orderBean.getDishId();
        MenuItem menu;
        try {
            menu = restTemplate.getForObject(menuServiceUrl, MenuItem.class);
        } catch (Exception e) {
            logger.error("Error fetching menu item from URL: {}", menuServiceUrl, e);
            throw new RuntimeException("Failed to fetch menu item", e);
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setDishId(menu.getDishId());
        order.setQuantity(orderBean.getQuantity());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setStatus("Placed");
        order.setCancelationTime(LocalDateTime.now().plusHours(2));
        order.setPrice(orderBean.getQuantity() * menu.getPrice());

        Order savedOrder;
        try {
            savedOrder = orderRepository.save(order);
            logger.info("Order successfully placed with ID: {}", savedOrder.getId());
        } catch (Exception e) {
            logger.error("Error saving order with details: {}", order, e);
            throw new RuntimeException("Failed to save order", e);
        }

       
        savedOrder.getCustomer().setOrders(null);
        return savedOrder;
    }

    public Order cancelOrder(Long orderId) throws OrderNotFoundException, InvalidOrderException {
        logger.info("Attempting to cancel order with ID: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        if (LocalDateTime.now().isAfter(order.getCancelationTime())) {
            logger.warn("Attempted to cancel order with ID: {} after cancellation time", orderId);
            throw new InvalidOrderException("Order cannot be cancelled as it is past the cancellation time");
        }

        order.setStatus("Cancelled");
        Order updatedOrder;
        try {
            updatedOrder = orderRepository.save(order);
            logger.info("Order successfully canceled with ID: {}", orderId);
        } catch (Exception e) {
            logger.error("Error updating order status to 'Cancelled' for order ID: {}", orderId, e);
            throw new RuntimeException("Failed to update order status", e);
        }

      
        updatedOrder.getCustomer().setOrders(null);
        return updatedOrder;
    }
}
