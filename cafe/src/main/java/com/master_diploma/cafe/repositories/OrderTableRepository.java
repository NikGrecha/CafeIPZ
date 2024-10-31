package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.OrderTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTableRepository extends CrudRepository<OrderTable, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE order_table SET status = :newValue WHERE id = :id", nativeQuery = true)
    Integer updateStatus(@Param("newValue") String status, @Param("id") Long id);
    @Transactional
    @Query(value = "SELECT * FROM order_table WHERE user_client_id = :id", nativeQuery = true)
    List<OrderTable> findByUserId(@Param("id") Long id);
}
