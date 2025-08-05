package com.security.learn.cqrsorderservice.repo;

import com.security.learn.cqrsorderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCommandRepository extends JpaRepository<Order, String> {

}