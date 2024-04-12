package com.conference.expandconverter.controllers;

import com.conference.expandconverter.dto.OrderDto;
import com.conference.expandconverter.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public Page<OrderDto> getOrdersV1(Pageable pageable, Set<String> expands) {
        return orderService.getOrdersV1(pageable, expands);
    }

    @Override
    public Page<OrderDto> getOrdersV2(Pageable pageable, Set<String> expands) {
        return orderService.getOrdersV2(pageable, expands);
    }

    @Override
    public Page<OrderDto> split(Pageable pageable) {
        return orderService.split(pageable);
    }

    @Override
    public void testExpands() {
        orderService.testExpands();
    }

}
