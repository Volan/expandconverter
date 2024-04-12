package com.conference.expandconverter.repositories;

import com.conference.expandconverter.model.OrderPosition;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderPositionRepository extends EntityGraphCrudRepository<OrderPosition, Long> {

    List<OrderPosition> findByOrderIdIn(Set<Long> ids, EntityGraph entityGraph);

}
