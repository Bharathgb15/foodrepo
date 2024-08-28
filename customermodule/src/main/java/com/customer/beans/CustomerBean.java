package com.customer.beans;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBean {

    private Long id;
    @NotNull(message="Name is mandatory")
    private String name;
    @Email
    @NotNull(message="email is required")
    private String email;
    
    @NotNull
    @NotBlank(message = "PhoneNumber is requried")
    private String phNumber;
    
    @NotBlank
    @NotNull
    private String address;

}
