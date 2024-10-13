package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.OrderTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTableRepository extends CrudRepository<OrderTable, Long> {
}
