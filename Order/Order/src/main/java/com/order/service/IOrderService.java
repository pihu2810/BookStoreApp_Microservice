package com.order.service;

import com.order.dto.OrderDTO;
import com.order.model.Order;

import java.util.List;

public interface IOrderService
{
    Order insertOrder(OrderDTO orderdto);

    Order getByID(int orderid);

    List<Order> getAll();

    Order updateById(int orderid,OrderDTO dto);

    Order deleteById(int orderid);
//    List<Order> getOrderRecord (String token);
//    List<Order> getAllOrderRecords(String token);
//
//    Order cancelOrder(String token,int userID);

}
