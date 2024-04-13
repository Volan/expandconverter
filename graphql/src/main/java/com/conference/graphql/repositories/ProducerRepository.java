package com.conference.graphql.repositories;

import com.conference.graphql.model.Producer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface ProducerRepository extends CrudRepository<Producer, Long>,
        QuerydslPredicateExecutor<Producer> {
}