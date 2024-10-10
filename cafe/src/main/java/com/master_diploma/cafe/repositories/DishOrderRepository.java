package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.DishOrder;
import org.springframework.data.repository.CrudRepository;

public interface DishOrderRepository extends CrudRepository<DishOrder, Long> {
}
