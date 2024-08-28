package com.customer.beans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBean {

    
    private Long customerId;
   
    private Long dishId;
    @NotNull(message = " Quantity can't  be zero")
    @Positive
    private int quantity;
    
    
    

    
}
