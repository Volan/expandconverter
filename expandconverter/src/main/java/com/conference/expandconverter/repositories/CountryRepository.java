package com.conference.expandconverter.repositories;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import com.conference.expandconverter.model.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends EntityGraphCrudRepository<Country, Long> {
}