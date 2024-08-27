package com.customer.service;

import java.util.List;

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

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    
    public Customer registerCustomer(CustomerBean customerBean) {
    	Customer customer =new Customer() ;
    	customer.setAddress(customerBean.getAddress());
    	customer.setEmail(customerBean.getEmail());
    	customer.setName(customerBean.getName());
    	customer.setPhNumber(customerBean.getPhNumber());
    	
            return customerRepository.save(customer);
    }

    public List<Order> getOrdersByCustomerId(Long customerId)  throws OrderNotFoundException{
       List<Order> order = orderRepository.findByCustomerId(customerId);
    		   if(order==null) {
    			    throw new OrderNotFoundException("Orders not found for customer with ID: " + customerId); 
    		   }
              return order; 
    }
}






















