package com.flyr.flyrdemo.service.impl;

import com.example.models.Product;
import com.flyr.flyrdemo.entity.GroupByCategory;
import com.flyr.flyrdemo.repository.FlyrProductRepository;
import com.flyr.flyrdemo.util.ProductHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.flyr.flyrdemo.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements  ProductService{

    private FlyrProductRepository flyrProductRepository;
    private ProductHelper productHelper;

    @Override
    public List<Product> getAllProducts() {
        //List<GroupByCategory> countByProductCategory = flyrProductRepository.countByProductCategory();
        //countByProductCategory.forEach(t->log.info("Category {}, Count {}", t.getCategory(), t.getCount()));

        return flyrProductRepository.findAll()
                .stream().map(t->productHelper.convertToProductDTO(t)).collect(Collectors.toList());
    }

    @Override
    public Product saveProduct(Product productDTO) {
        return productHelper.convertToProductDTO(flyrProductRepository.save(productHelper.convertToProductEntity(productDTO)));
    }
}
