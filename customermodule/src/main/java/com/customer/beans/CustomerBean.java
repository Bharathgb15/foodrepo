package com.customer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBean {

    private Long id;
    private String name;
    private String email;
    private String phNumber;
    private String address;

}
