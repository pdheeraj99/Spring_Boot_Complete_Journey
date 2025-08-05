package com.security.learn.cqrsorderservice.controller;

import com.security.learn.cqrsorderservice.dto.OrderSummaryDTO;
import com.security.learn.cqrsorderservice.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders/queries") // We group all query endpoints together
public class OrderQueryController {

    @Autowired
    private OrderQueryService orderQueryService;

    @GetMapping("/all")
    public List<OrderSummaryDTO> getAllOrders() {
        return orderQueryService.handleFindAllOrdersQuery();
    }
}