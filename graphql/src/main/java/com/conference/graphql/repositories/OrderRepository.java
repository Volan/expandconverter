package com.conference.graphql.repositories;

import com.conference.graphql.model.Order;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>,
        QuerydslPredicateExecutor<Order> {
}