package com.cart.service;

import com.cart.Util.EmailSenderService;
import com.cart.Util.TokenUtility;
import com.cart.dto.CartDTO;
import com.cart.exception.BookStoreException;
import com.cart.model.Cart;
import com.cart.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService  implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    RestTemplate restTemplate;

    /**
     * create a method name as insertToCart
     * Ability to save cart details to repository
     * */
    @Override
    public Cart insertToCart(CartDTO dto) {
        Cart cart = new Cart(dto);
        cartRepository.save(cart);
        return cart;
    }

    /**
     * create a method name as getAllCarts
     * Ability to save cart details to repository
     * */
    @Override
    public List<Cart> getAllCarts() {
        List<Cart> list = cartRepository.findAll();
        if(list.isEmpty()) {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"there are no Carts inserted yet");
        }
        return list;
    }

    /**
     * create a method name as getCartDetailsById by token
     * Ability to save cart details to repository
     * */
    @Override
    public Cart getCartByID(int cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isEmpty()) {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"There are no carts with given id");
        }
        return cart.get();
    }

    /**
     * create a method name as updateById by token
     * Ability to save cart details to repository
     * */
    @Override
    public Cart updateById(int cartId, CartDTO dto) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()) {
            Cart newCart = new Cart(cartId,dto);
            cartRepository.save(newCart);
            return newCart;
        }
        else {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"cart not found");
        }
    }

    /**
     * create a method name as deleteById by token
     * Ability to save cart details to repository
     * */
    @Override
    public Cart deleteById(int cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isEmpty()) {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"Invalid CartID..please input valid Id");
        }
        cartRepository.deleteById(cartId);
        return cart.get();
    }
}
