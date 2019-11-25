package com.murphy1.foodlogger.repositories;

import com.murphy1.foodlogger.model.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Category, String> {
}
