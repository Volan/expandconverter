package com.conference.expandconverter.repositories;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import com.conference.expandconverter.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends EntityGraphCrudRepository<Item, Long> {
}
