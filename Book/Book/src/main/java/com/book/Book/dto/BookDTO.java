package com.book.Book.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public @Data class BookDTO
{
    public String bookName;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Authername Invalid ")
    @NotEmpty(message = "Auther name cannot be null")
    public String autherName;

    public String bookDescription;

    public  String bookImg;

    public int price;

    public int quantity;
}
