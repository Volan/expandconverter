package com.conference.expandconverter.repositories;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import com.conference.expandconverter.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends EntityGraphCrudRepository<Person, Long> {
}
