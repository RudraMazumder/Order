package com.flyr.flyrdemo.repository;

import com.flyr.flyrdemo.entity.GroupByCategory;
import com.flyr.flyrdemo.entity.Order;
import com.flyr.flyrdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface FlyrProductRepository extends JpaRepository<Product, Integer> {


    @Query(value = "SELECT PRODUCT_CATEGORY as category, COUNT(*) as count FROM FLYR_PRODUCT group by PRODUCT_CATEGORY",
            nativeQuery = true)
    public List<GroupByCategory> countByProductCategory();

}
