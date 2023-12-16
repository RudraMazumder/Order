package com.flyr.flyrdemo.util;

import com.flyr.flyrdemo.entity.Product;
import com.flyr.flyrdemo.repository.FlyrProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ProductHelper {

    private FlyrProductRepository flyrProductRepository;
    public Product convertToProductEntity(com.example.models.Product product){

        String currency = null;
        switch (product.getCurrency()){
            case EUR -> currency="EUR";
            case INR -> currency="INR";
            case USD -> currency="USD";
            default -> currency=null;

        }

        String category = null;
        switch (product.getCategory()){
            case TV -> category="TV";
            case PHONE -> category="PHONE";
            case OTHERS -> category="OTHERS";
            case REFRIGERATOR -> category="REFRIGERATOR";
            default -> category="OTHERS";
        }

        return Product.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .productCurrency(currency)
                .productCategory(category)
                .build();
    }

    public com.example.models.Product convertToProductDTO(Product product){
        com.example.models.Product productDTO = new com.example.models.Product();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        String currency = product.getProductCurrency();

        switch (currency){
            case "USD" -> productDTO.setCurrency(com.example.models.Product.CurrencyEnum.USD);
            case "INR" -> productDTO.setCurrency(com.example.models.Product.CurrencyEnum.INR);
            case "EUR" -> productDTO.setCurrency(com.example.models.Product.CurrencyEnum.EUR);
            default -> productDTO.setCurrency(null);
        }

        String category = product.getProductCategory();
        switch (category){
            case "TV" -> productDTO.setCategory(com.example.models.Product.CategoryEnum.TV);
            case "PHONE" -> productDTO.setCategory(com.example.models.Product.CategoryEnum.PHONE);
            case "REFRIGERATOR" -> productDTO.setCategory(com.example.models.Product.CategoryEnum.REFRIGERATOR);
            default -> productDTO.setCategory(com.example.models.Product.CategoryEnum.OTHERS);

        }

        return productDTO;
    }

    public Boolean validateProduct(com.example.models.Product productDTO){
        return flyrProductRepository.existsById(productDTO.getProductId());
    }
}
