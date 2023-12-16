package com.flyr.flyrdemo.util;

import com.flyr.flyrdemo.entity.Order;
import com.flyr.flyrdemo.repository.FlyrProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class OrderHelper {

    private ProductHelper productHelper;
    private FlyrProductRepository flyrProductRepository;

    public com.example.models.Order convertToOrderDTO(Order order){
        com.example.models.Order orderDTO= new com.example.models.Order();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomerName(order.getCustomerName());
        log.info("Products count for this order {}", order.getProducts().size());

        orderDTO.setProducts(order.getProducts().stream()
                .map(t->productHelper.convertToProductDTO(t))
                        .peek(t->log.info("Product Name - {}", t.getProductName()))
                .collect(Collectors.toList()));
        return orderDTO;
    }

    public  Order convertToOrderEntity(com.example.models.Order orderDTO){
        return Order.builder()
                .customerName(orderDTO.getCustomerName())
                //.products(orderDTO.getProducts().stream().map(t->productHelper.convertToProductEntity(t)).collect(Collectors.toSet()))
                .products(orderDTO.getProducts().stream()
                        .map(t->flyrProductRepository.findById(t.getProductId()).get())
                        .peek(t->log.info("Product Name is {}", t.getProductName()))
                        .collect(Collectors.toSet()))
                .createdOn(Date.valueOf(LocalDate.now()))
                .build();
    }

    public Boolean validateOrder(com.example.models.Order orderDTO) {
        if(orderDTO==null || orderDTO.getProducts()==null || orderDTO.getProducts().isEmpty())
            return false;
        if(!orderDTO.getProducts().stream().anyMatch(product -> productHelper.validateProduct(product)))
            return false;
        else
            return true;
    }
}
