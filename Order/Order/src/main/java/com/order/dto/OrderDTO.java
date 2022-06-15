package com.order.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderDTO
{
    private int orderid;
    private int quantity;
    @NotEmpty(message="Please provide address")
    private String address;
    private int userID;
    private int bookId;
    private boolean cancel;
    private int price;
}
