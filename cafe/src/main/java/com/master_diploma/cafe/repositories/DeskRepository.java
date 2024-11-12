package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.dto.DeskReserveDto;
import com.master_diploma.cafe.dto.DeskReserveProjection;
import com.master_diploma.cafe.models.Desk;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Iterator;
import java.util.List;

@Repository
public interface DeskRepository extends CrudRepository<Desk, Long> {
    List<Desk> findByInstitutionId(Long id);

    @Query(value = "SELECT * FROM desk WHERE institution_id = :institutionId ORDER BY RANDOM() LIMIT 1;", nativeQuery = true)
    Desk findRandomByInstitutionId(long institutionId);

    @Query(value = "select desk.id, number_of_seats, status, institution_id, date_of_reserve from desk\n" +
            "left join reserve on desk.id=reserve.desk_id where institution_id = :id", nativeQuery = true)
    List<DeskReserveProjection> findDeskReserveByInstitutionId(@Param("id") Long institutionId);
}
