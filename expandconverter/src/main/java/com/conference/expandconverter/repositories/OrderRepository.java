package com.conference.expandconverter.repositories;

import com.conference.expandconverter.model.Order;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends EntityGraphPagingAndSortingRepository<Order, Long> {
}