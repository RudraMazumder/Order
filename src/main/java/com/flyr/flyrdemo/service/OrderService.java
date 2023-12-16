package com.flyr.flyrdemo.service;

import org.apache.coyote.BadRequestException;

import java.util.List;

public interface OrderService {
    public List<com.example.models.Order> getAllOrders();

    public com.example.models.Order getOrderById(Integer id) throws BadRequestException;

    public List<com.example.models.Product> getProductsByOrderId(Integer id) throws BadRequestException;

    public com.example.models.Order saveOrder(com.example.models.Order orderDTO) throws BadRequestException;

    public void addProductForAnOrder(Integer orderCode, com.example.models.Product productDTO) throws BadRequestException;
}
