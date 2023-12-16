package com.flyr.flyrdemo.controller;

import com.example.models.Product;
import com.flyr.flyrdemo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController implements com.example.api.ProductsApi {

    private ProductService productService;
    @Override
    public ResponseEntity<List<Product>> productsGet() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> productsIdDelete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Product> productsIdGet(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> productsPost(Product body) {
        productService.saveProduct(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
