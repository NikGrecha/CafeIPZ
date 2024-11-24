package com.master_diploma.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerScheduleDTO {
    private Long workScheduleId;
    private Long workerId;
    private Long userInstitutionId;
    private String workerFirstName;
    private String workerLastName;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private LocalTime hoursOfWork;
}
