package com.flyr.flyrdemo.controller;


import com.example.models.Order;
import com.example.models.Product;
import com.flyr.flyrdemo.service.OrderService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController implements com.example.api.OrdersApi {

    private OrderService orderService;

    @Override
    public ResponseEntity<List<Order>> ordersGet() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> ordersIdDelete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Order> ordersIdGet(Integer id) {
        try {
            return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
        }catch(BadRequestException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Void> ordersIdProductsDelete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> ordersIdProductsGet(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> ordersIdProductsPost(Integer id, Product body)  {
        try {
            orderService.addProductForAnOrder(id, body);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(BadRequestException ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> ordersPost(Order body) {
        try {
            orderService.saveOrder(body);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
