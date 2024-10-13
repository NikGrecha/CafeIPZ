package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.DishOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishOrderRepository extends CrudRepository<DishOrder, Long> {
}
