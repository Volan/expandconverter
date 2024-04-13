package com.conference.graphql.repositories;

import com.conference.graphql.model.OrderPosition;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface OrderPositionRepository extends CrudRepository<OrderPosition, Long>,
        QuerydslPredicateExecutor<OrderPosition> {
}
