package com.flyr.flyrdemo.service;

import java.util.List;

public interface ProductService {
    public List<com.example.models.Product> getAllProducts();
    public com.example.models.Product saveProduct(com.example.models.Product productDTO);
}
