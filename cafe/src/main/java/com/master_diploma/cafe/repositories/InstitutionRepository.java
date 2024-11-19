package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Institution;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long> {
    @Transactional
    @Query(value = "SELECT i.* FROM institution i\n" +
            "JOIN user_institution ui ON i.id = ui.institution_id\n" +
            "WHERE ui.user_worker_id = :ownerId", nativeQuery = true)
    List<Institution> findInstitutionByOwner(@Param("ownerId") Long ownerId);
    @Transactional
    @Query(value = "SELECT i.* FROM user_institution ui\n" +
            "JOIN institution i ON ui.institution_id = i.id\n" +
            "WHERE ui.user_worker_id = :workerId", nativeQuery = true)
    Institution findByWorkerId(@Param("workerId") Long workerId);
}
