package com.conference.graphql.repositories;

import com.conference.graphql.model.Country;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface CountryRepository extends CrudRepository<Country, Long>,
        QuerydslPredicateExecutor<Country> {
}