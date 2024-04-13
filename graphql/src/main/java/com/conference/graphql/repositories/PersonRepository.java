package com.conference.graphql.repositories;

import com.conference.graphql.model.Person;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface PersonRepository extends CrudRepository<Person, Long>,
        QuerydslPredicateExecutor<Person> {
}
