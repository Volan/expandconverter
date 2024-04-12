package com.conference.expandconverter.repositories;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import com.conference.expandconverter.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends EntityGraphCrudRepository<Address, Long> {
}