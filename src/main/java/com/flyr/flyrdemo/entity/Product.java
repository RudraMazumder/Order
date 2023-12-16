package com.flyr.flyrdemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FLYR_PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_CATEGORY")
    private String productCategory;

    @Column(name = "PRODUCT_CURRENCY")
    private String productCurrency;

    @Column(name = "PRODUCT_PRICE")
    private Integer price;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    /*@OneToMany(mappedBy = "product")
    private Set<Item> items = new HashSet<>();*/


}
