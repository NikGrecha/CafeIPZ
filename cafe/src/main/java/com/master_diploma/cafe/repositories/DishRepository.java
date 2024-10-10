package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Long> {
}
