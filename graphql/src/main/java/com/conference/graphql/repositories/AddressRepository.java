package com.conference.graphql.repositories;

import com.conference.graphql.model.Address;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface AddressRepository extends CrudRepository<Address, Long>,
        QuerydslPredicateExecutor<Address> {
}