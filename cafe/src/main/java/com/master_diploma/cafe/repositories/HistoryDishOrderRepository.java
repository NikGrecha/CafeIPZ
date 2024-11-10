package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.HistoryDishOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDishOrderRepository extends CrudRepository<HistoryDishOrder, Long> {
}
