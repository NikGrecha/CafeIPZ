package com.master_diploma.cafe.dto;

import java.time.LocalDateTime;

public interface DeskReserveProjection {
    long getId();
    int getNumberOfSeats();
    String getStatus();
    long getInstitutionId();
    LocalDateTime getDateOfReserve();
}
