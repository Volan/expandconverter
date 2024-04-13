package com.conference.graphql.repositories;

import com.conference.graphql.model.DeliveryType;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface DeliveryTypeRepository extends CrudRepository<DeliveryType, Long>,
        QuerydslPredicateExecutor<DeliveryType> {
}