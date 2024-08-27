package com.customer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBean {

    
    private Long customerId;
    private Long dishId;
    private int quantity;
    
}
