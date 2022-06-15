package com.cart.model;

import com.cart.dto.CartDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Cart_db")
public @Data
class Cart
{
    @Id
    @GeneratedValue
    private int cartId;
    private int userID;
    private int bookId;
    private int quantity;

    public Cart(CartDTO dto) {
        this.userID = dto.getUserID();
        this.bookId = dto.getBookId();
        this.quantity = dto.getQuantity();
    }

    public Cart(Integer cartId, CartDTO dto) {
        super();
        this.cartId=cartId;
        this.userID = dto.getUserID();
        this.bookId = dto.getBookId();
        this.quantity = dto.getQuantity();
    }


    public Cart()
    {

    }

}
