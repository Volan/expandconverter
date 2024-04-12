package com.conference.expandconverter.repositories;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphCrudRepository;
import com.conference.expandconverter.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends EntityGraphCrudRepository<Category, Long> {
}