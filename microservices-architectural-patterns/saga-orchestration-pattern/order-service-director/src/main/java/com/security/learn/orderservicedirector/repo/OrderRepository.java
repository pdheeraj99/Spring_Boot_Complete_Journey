package com.security.learn.orderservicedirector.repo;

import com.security.learn.orderservicedirector.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}