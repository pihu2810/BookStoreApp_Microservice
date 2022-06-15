package com.order.service;

import com.order.Util.EmailSenderService;
import com.order.Util.TokenUtility;
import com.order.dto.OrderDTO;
import com.order.exception.BookStoreException;
import com.order.exception.OrderException;
import com.order.exception.UserException;
import com.order.model.BookContact;
import com.order.model.Order;
import com.order.model.UserContact;
import com.order.respository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService
{
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    RestTemplate restTemplate;

    /**
     * create a method name as insertOrder
     * Ability to save order details to repository
     * */
    @Override
    public Order insertOrder(OrderDTO orderdto) {
       UserContact user = restTemplate.getForObject("http://localhost:9005/userregistration/findById/" + orderdto.getUserID(), UserContact.class);
        if(user.equals(null)) {
            throw new BookStoreException("Invalid user id...please provide valid user id");
        }
        else
        {
            BookContact book = restTemplate.getForObject("http://localhost:9006/book/getBook/" + orderdto.getBookId(), BookContact.class);
            if(book.equals(null))
            {
                throw new BookStoreException("Invalid book id...please provide valid book id");
            }
            else
            {
                if(orderdto.getQuantity() >(int)book.getQuantity())
                {
                    throw new OrderException("Currently we dont have that much books available");
                }
                else
                {
                    Order order = new Order(orderdto);
                    order.setPrice(orderdto.getQuantity()*book.getPrice());
                    order.setBookId(book.getBookId());
                    order.setDate(LocalDate.now());
                    orderRepo.save(order);
                    return order;
                }
            }
        }
    }

    /**
     * create a method name as getByID by orderid
     * Ability to save order details to repository
     * */
    @Override
    public Order getByID(int orderid) {
        Optional<Order> order = orderRepo.findById(orderid);
        if(order.isPresent()) {
            throw new OrderException("Invalid order id...please provide valid Order id");
        }
        return order.get();
    }

    /**
     * create a method name as getAll
     * Ability to save order details to repository
     * */
    @Override
    public List<Order> getAll() {
        List<Order> orders =  orderRepo.findAll();
        return orders;
    }

    /**
     * create a method name as updateById by orderid
     * Ability to save order details to repository
     * */
    @Override
    public Order updateById(int orderid, OrderDTO dto) {
        Optional<Order> order = orderRepo.findById(orderid);
        if(order.isPresent()) {
            throw new BookStoreException("Invalid Order Id...please provide valid Order id");
        }
       UserContact user = restTemplate.getForObject("http://localhost:9005/user/findById/" + dto.getUserID(), UserContact.class);
        if(user.equals(null)) {
            throw new UserException("Invalid user id...please provide valid user id");
        }
        BookContact book = restTemplate.getForObject("http://localhost:9006/book/getBook/" + dto.getBookId(), BookContact.class);
        if(book.equals(null)) {
            throw new BookStoreException(HttpStatus.NOT_FOUND,"Invalid book id...please provide valid book id");
        }
        Order newOrder = new Order(dto);
        newOrder.setPrice(dto.getQuantity()*book.getPrice());
        orderRepo.save(newOrder);
        return newOrder;
    }

    /**
     * create a method name as deleteById by orderid
     * */
    @Override
    public Order deleteById(int orderid) {
        Optional<Order> order = orderRepo.findById(orderid);
        if(order.isPresent()) {
            throw new OrderException("Invalid Order Id...please provide valid Order id");
        }
        orderRepo.delete(order.get());
        return order.get();
    }

}

