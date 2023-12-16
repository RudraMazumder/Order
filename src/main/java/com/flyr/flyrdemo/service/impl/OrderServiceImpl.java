package com.flyr.flyrdemo.service.impl;



import com.example.models.Order;
import com.example.models.Product;
import com.flyr.flyrdemo.repository.FlyrOrderRepository;
import com.flyr.flyrdemo.repository.FlyrProductRepository;
import com.flyr.flyrdemo.service.OrderService;
import com.flyr.flyrdemo.util.OrderHelper;
import com.flyr.flyrdemo.util.ProductHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private FlyrOrderRepository orderRepository;
    private FlyrProductRepository flyrProductRepository;
    private OrderHelper orderHelper;
    private ProductHelper productHelper;

    public List<com.example.models.Order> getAllOrders(){
        return orderRepository.findAll().stream().map(t->orderHelper.convertToOrderDTO(t)).collect(Collectors.toList());
    }

    @Override
    public Order getOrderById(Integer id) throws BadRequestException{
        return orderHelper.convertToOrderDTO(orderRepository.findById(id).orElseThrow(()->new BadRequestException("ID NOT FOUND")));
    }

    @Override
    public List<Product> getProductsByOrderId(Integer id) throws BadRequestException {
        com.flyr.flyrdemo.entity.Order orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("NO_ORDER_FOUND"));

        return orderEntity.getProducts().stream().map(t->productHelper.convertToProductDTO(t)).collect(Collectors.toList());

    }

    @Transactional
    public com.example.models.Order saveOrder(com.example.models.Order orderDTO) throws BadRequestException{

        if(orderDTO==null || orderDTO.getProducts()==null
                || orderDTO.getProducts().isEmpty() || !orderHelper.validateOrder(orderDTO)) {
            log.error("Incorrect order request");
            throw new BadRequestException("INVALID_ORDER");
        }


        orderRepository.save(orderHelper.convertToOrderEntity(orderDTO));

        return orderDTO;


    }

    @Override
    public void addProductForAnOrder(Integer orderCode, com.example.models.Product productDTO) throws BadRequestException {

        if(!productHelper.validateProduct(productDTO))
            throw new BadRequestException("INVALID_PRODUCT");
        com.flyr.flyrdemo.entity.Order orderEntity = orderRepository.findById(orderCode).orElseThrow(() -> new BadRequestException("INVALID_ORDER"));

        com.flyr.flyrdemo.entity.Product product = flyrProductRepository.findById(productDTO.getProductId()).get();
        orderEntity.getProducts().add(product);
        orderRepository.save(orderEntity);

    }



}
