package com.alicjawaclawek.ksiazki.repository;

import com.alicjawaclawek.ksiazki.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
