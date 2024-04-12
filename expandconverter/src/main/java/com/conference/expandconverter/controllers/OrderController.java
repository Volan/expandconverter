package com.conference.expandconverter.controllers;

import com.conference.expandconverter.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

public interface OrderController {

    @RequestMapping("api/v1/orders")
    Page<OrderDto> getOrdersV1(@PageableDefault Pageable pageable,
                             @RequestParam(value = "expand", required = false) Set<String> expands);

    @RequestMapping("api/v2/orders")
    Page<OrderDto> getOrdersV2(@PageableDefault Pageable pageable,
                             @RequestParam(value = "expand", required = false) Set<String> expands);

    @RequestMapping("api/v2/orders/split")
    Page<OrderDto> split(@PageableDefault Pageable pageable);

    @RequestMapping("api/v1/testExpands")
    void testExpands();


}
