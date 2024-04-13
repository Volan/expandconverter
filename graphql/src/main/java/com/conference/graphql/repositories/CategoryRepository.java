package com.conference.graphql.repositories;

import com.conference.graphql.model.Category;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface CategoryRepository extends CrudRepository<Category, Long>,
        QuerydslPredicateExecutor<Category> {
}