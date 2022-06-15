package com.order.controller;

import com.order.dto.OrderDTO;
import com.order.dto.ResponseDTO;
import com.order.model.Order;
import com.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("order")
public class OrderController
{
    @Autowired
    private IOrderService orderService;

    /**
     * Inserting the data to the order repository
     * @param orderdto - data of the order
     * @return - data of the order
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> addOrder(@RequestBody OrderDTO dto){
        Order order = orderService.insertOrder(dto);
        ResponseDTO response = new ResponseDTO("Order Placed : ", order);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }

    /**
     * Ability to get all data from the order repository
     * @return -list of orders
     */
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll(){
        List<Order> list = orderService.getAll();
        ResponseDTO response = new ResponseDTO("Orders Placed : ", list);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

    }

    /**
     * Ability to cal api to retrieve order record by id
     * @param orderid - represent  id
     * @return - order of particular id entered by user
     */
    @GetMapping("/getById/{orderid}")
    public ResponseEntity<ResponseDTO> getById( @Valid @PathVariable int orderid){
        Order order = orderService.getByID(orderid);
        ResponseDTO response = new ResponseDTO("Order Retrieved : ", order);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);

    }

    @PutMapping("/update/{orderid}")
    public ResponseEntity<ResponseDTO> updateById( @Valid@PathVariable int orderid,@RequestBody OrderDTO dto){
        Order order = orderService.updateById(orderid,dto);
        ResponseDTO response = new ResponseDTO("Order Updated : ", order);
        return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
    }

    /**
     * Ability to delete order of particular id entered by user
     * @param orderid - order id
     * @return - cancel order by particular id
     */
    @DeleteMapping("/delete/{orderid}")
    public ResponseEntity<ResponseDTO> deleteById(@Valid@PathVariable int orderid) {
        Order order = orderService.deleteById(orderid);
        ResponseDTO response = new ResponseDTO("Order deleted : ", order);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
}
