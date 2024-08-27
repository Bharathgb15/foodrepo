package com.customer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    private int dishId;
    private String dishType;
    private String dishName;
    private Integer price;
    private String dishDesc;

}
