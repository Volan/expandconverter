package com.conference.graphql.repositories;

import com.conference.graphql.model.Item;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface ItemRepository extends CrudRepository<Item, Long>,
        QuerydslPredicateExecutor<Item> {
}
