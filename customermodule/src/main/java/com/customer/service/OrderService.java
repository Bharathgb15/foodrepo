package com.customer.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

@Service
public class OrderService  {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

   @Autowired
   RestTemplate restTemplate;

    public Order placeOrder(OrderBean orderBean) {
        Customer customer = customerRepository.findById(orderBean.getCustomerId())
                .orElseThrow(() -> new OrderNotFoundException("Customer not found with id: " + orderBean.getCustomerId()));

        String menuServiceUrl = "http://localhost:8083/app1/dish/" + orderBean.getDishId();
	

		MenuItem menu = restTemplate.getForObject(menuServiceUrl, MenuItem.class);
       
        Order order = new Order();
        order.setCustomer(customer);
        order.setDishId(menu.getDishId());    
        order.setQuantity(orderBean.getQuantity());
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setStatus("Placed"); 
        order.setCancelationTime(LocalDateTime.now().plusHours(2));
        order.setPrice(orderBean.getQuantity()*menu.getPrice()); 
        Order orders= orderRepository.save(order);
        orders.getCustomer().setOrders(null);
        return orders;
        
    }

    public Order cancelOrder(Long orderId) throws OrderNotFoundException, InvalidOrderException{
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        if(LocalDateTime.now().isAfter(order.getCancelationTime())) {
        	throw new InvalidOrderException("It can not be Cancelled");
        }
        order.setStatus("Cancelled");
        orderRepository.save(order);
        order.getCustomer().setOrders(null);
		return order;
    }

}
