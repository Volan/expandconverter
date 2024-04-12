package com.conference.expandconverter.services;

import com.conference.expandconverter.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface OrderService {

    void testExpands();

    Page<OrderDto> getOrdersV1(Pageable pageable, Set<String> expands);

    Page<OrderDto> getOrdersV2(Pageable pageable, Set<String> expands);

    Page<OrderDto> split(Pageable pageable);

}
