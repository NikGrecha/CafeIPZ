package com.master_diploma.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerInstitutionDTO {
    private Long workerId;
    private Long userInstitutionId;
    private String firstName;
    private String lastName;
}
