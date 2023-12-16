package com.flyr.flyrdemo.repository;

import com.flyr.flyrdemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlyrOrderRepository extends JpaRepository<Order, Integer> {

}
