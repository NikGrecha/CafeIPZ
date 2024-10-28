package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Desk;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public interface DeskRepository extends CrudRepository<Desk, Long> {
    @Transactional
    @Query(name = "SELECT * FROM desk WHERE institution_id = :id")
    Iterator<Desk> findByInstitutionId(@Param("id") Long id);
}
