package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.OrderTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTableRepository extends CrudRepository<OrderTable, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE order_table SET status = :newValue WHERE id = :id", nativeQuery = true)
    Integer updateStatus(@Param("newValue") String status, @Param("id") Long id);
}
