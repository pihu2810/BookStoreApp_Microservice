package com.cart.service;

import com.cart.dto.CartDTO;
import com.cart.model.Cart;

import javax.validation.Valid;
import java.util.List;

public interface ICartService
{
    Cart insertToCart(@Valid CartDTO dto);

    List<Cart> getAllCarts();

    Cart getCartByID(int cartId);

    Cart updateById(int cartId, @Valid CartDTO dto);

    Cart deleteById(int cartId);
//    String insertItems(CartDTO cartdto);
//    List<Cart> getCartDetails(String token);
//    Cart getCartDetailsById(String token);
//    void deleteCartItemById(String token);
//    Cart updateRecordById(String token, CartDTO cartDTO);
}
