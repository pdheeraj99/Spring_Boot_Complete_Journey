package com.security.learn.cqrsorderservice.repo;

import com.security.learn.cqrsorderservice.dto.OrderSummaryDTO;
import com.security.learn.cqrsorderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//  Class-based projection
// We extend JpaRepository just to make it a Spring Data bean, but we'll add custom queries.
public interface OrderQueryRepository extends JpaRepository<Order, String> {

    // This is the core of our read-side. A custom, efficient query.
    @Query("SELECT new com.security.learn.cqrsorderservice.dto.OrderSummaryDTO(o.orderId, o.productId, o.status) FROM Order o")
    List<OrderSummaryDTO> findAllSummaries();
}