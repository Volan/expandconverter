package com.conference.expandconverter.repositories;

import com.conference.expandconverter.model.Producer;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends EntityGraphCrudRepository<Producer, Long> {
}