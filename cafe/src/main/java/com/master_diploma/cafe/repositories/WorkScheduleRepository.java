package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.dto.WorkerScheduleDTO;
import com.master_diploma.cafe.models.WorkSchedule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface WorkScheduleRepository extends CrudRepository<WorkSchedule, Long> {

    @Query(value = """
            SELECT new com.master_diploma.cafe.dto.WorkerScheduleDTO(ws.id, u.id, ui.id, u.firstName, u.lastName, ws.dateTimeStart, ws.dateTimeEnd, ws.hoursOfWork) FROM WorkSchedule ws
            JOIN ws.userInstitution ui
            JOIN ui.user u
            WHERE ui.institution.id = :institutionId
            ORDER BY ws.dateTimeStart DESC""")
    List<WorkerScheduleDTO> findWorkerScheduleByInstitutionId(Long institutionId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE com.master_diploma.cafe.models.WorkSchedule ws SET ws.hoursOfWork = :hoursOfWork WHERE ws.id = :workScheduleId")
    void updateHoursOfWork(Long workScheduleId, LocalTime hoursOfWork);
}
