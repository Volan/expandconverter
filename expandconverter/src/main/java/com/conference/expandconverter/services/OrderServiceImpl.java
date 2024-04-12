package com.conference.expandconverter.services;

import com.conference.expandconverter.converters.v1.AbstractExpandConverter;
import com.conference.expandconverter.converters.v2.BetterAbstractExpandConverter;
import com.conference.expandconverter.dto.OrderDto;
import com.conference.expandconverter.dto.OrderPositionDto;
import com.conference.expandconverter.model.Order;
import com.conference.expandconverter.model.OrderPosition;
import com.conference.expandconverter.repositories.OrderPositionRepository;
import com.conference.expandconverter.repositories.OrderRepository;
import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final AbstractExpandConverter<Order, OrderDto> orderDtoExpandConverter;

    private final BetterAbstractExpandConverter<Order, OrderDto> betterOrderDtoExpandConverter;

    private final BetterAbstractExpandConverter<OrderPosition, OrderPositionDto> betterOrderPositionDtoExpandConverter;

    private final OrderPositionRepository orderPositionRepository;

    @Override
    public void testExpands() {
        Page<Order> orders = orderRepository.findAll(Pageable.ofSize(10));
        orders = orderRepository.findAll(Pageable.ofSize(10), DynamicEntityGraph.loading(List.of("person")));
        orders = orderRepository.findAll(Pageable.ofSize(10), DynamicEntityGraph.loading(List.of("person.address")));
    }

    @Override
    public Page<OrderDto> getOrdersV1(Pageable pageable, Set<String> expands) {
        Page<Order> orders = (Objects.isNull(expands) || expands.isEmpty()  ?
                orderRepository.findAll(pageable) :
                orderRepository.findAll(pageable, DynamicEntityGraph.loading(List.copyOf(expands))));
        return orders.map(order -> orderDtoExpandConverter.convert(order, expands));
    }

    @Override
    public Page<OrderDto> getOrdersV2(Pageable pageable, Set<String> expands) {
        Page<Order> orders = (Objects.isNull(expands) || expands.isEmpty()  ?
                orderRepository.findAll(pageable) :
                orderRepository.findAll(pageable, DynamicEntityGraph.loading(List.copyOf(expands))));
        return orders.map(order -> betterOrderDtoExpandConverter.convert(order, expands));
    }

    @Override
    public Page<OrderDto> split(Pageable pageable) {
        // Запрашиваем данные по заказу
        Set<String> orderExpands = Set.of("person.address", "deliveryType");
        Page<Order> orders = orderRepository.findAll(pageable, DynamicEntityGraph.loading(List.copyOf(orderExpands)));
        Page<OrderDto> orderDtos = orders.map(order -> betterOrderDtoExpandConverter.convert(order, orderExpands));
        // Запрашиваем данные по позиции
        Set<Long> orderIds = orderDtos.stream().map(OrderDto::getId).collect(Collectors.toSet());
        Set<String> orderPositionExpands = Set.of("item.category");
        List<OrderPosition> orderPositions = orderPositionRepository.findByOrderIdIn(orderIds, DynamicEntityGraph.loading(List.copyOf(orderPositionExpands)));
        Map<Long, List<OrderPositionDto>> orderPositionDtos = orderPositions.stream().map(position -> betterOrderPositionDtoExpandConverter.convert(position, orderPositionExpands))
                .collect(groupingBy(OrderPositionDto::getOrderId, mapping(Function.identity(), toList())));
        // Объединяем
        orderDtos.map(orderDto -> {
            orderDto.setPositions(orderPositionDtos.get(orderDto.getId()));
            return orderDto;
        });
        return orderDtos;
    }

}
