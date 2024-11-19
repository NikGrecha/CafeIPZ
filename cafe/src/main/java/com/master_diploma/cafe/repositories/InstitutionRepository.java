package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Institution;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long> {
    @Transactional
    @Query(value = """
            SELECT i.* FROM institution i
            JOIN user_institution ui ON i.id = ui.institution_id
            WHERE ui.user_worker_id = :ownerId""", nativeQuery = true)
    List<Institution> findInstitutionByOwner(@Param("ownerId") Long ownerId);
    @Transactional
    @Query(value = """
            SELECT i.* FROM user_institution ui
            JOIN institution i ON ui.institution_id = i.id
            WHERE ui.user_worker_id = :workerId""", nativeQuery = true)
    Institution findByWorkerId(@Param("workerId") Long workerId);

    @Transactional
    @Query(value = """
            SELECT i.* FROM reserve r
            JOIN desk d ON r.desk_id=d.id
            JOIN institution i ON d.institution_id=i.id
            WHERE r.id = :reserveId""", nativeQuery = true)
    Optional<Institution> findByReserveId(Long reserveId);
}
