package com.order.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public @Data
class UserDTO
{
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Firstname Invalid ")
    @NotEmpty(message = "first name cannot be null")
    public String firstName;

    @Pattern(regexp = "^[A-Z]{1,}[a-zA-z\\s]{2,}$", message = "Lastname Invalid!!! ")
    @NotEmpty(message = "last name cannot be null")
    public String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$", message = "EmailInvalid")
    @NotEmpty(message = "Email cannot be null")
    public String email;

    @Pattern(regexp = "^[A-Za-z,.0-9]{3,}$", message = "Address Invalid!!! ")
    @NotEmpty(message = "Address cannot be null")
    public String address;


    @NotEmpty(message = "Password cannot be null")
    public String password;

    public  boolean verified;
}
