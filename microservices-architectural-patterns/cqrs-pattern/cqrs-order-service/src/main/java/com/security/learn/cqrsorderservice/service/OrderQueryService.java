package com.security.learn.cqrsorderservice.service;

import com.security.learn.cqrsorderservice.dto.OrderSummaryDTO;
import com.security.learn.cqrsorderservice.repo.OrderQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryService {
    @Autowired
    private OrderQueryRepository orderQueryRepository;

    public List<OrderSummaryDTO> handleFindAllOrdersQuery() {
        System.out.println("QUERY: Fetching all order summaries.");
        return orderQueryRepository.findAllSummaries();
    }
}