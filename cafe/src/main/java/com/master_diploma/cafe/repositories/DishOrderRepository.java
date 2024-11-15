package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.DishOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishOrderRepository extends CrudRepository<DishOrder, Long> {
    List<DishOrder> findByOrderId(Long orderId);
}
