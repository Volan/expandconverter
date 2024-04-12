package com.conference.expandconverter.repositories;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import com.conference.expandconverter.model.DeliveryType;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends EntityGraphCrudRepository<DeliveryType, Long> {
}