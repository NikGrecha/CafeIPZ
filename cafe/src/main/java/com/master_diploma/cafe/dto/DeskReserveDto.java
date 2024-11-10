package com.master_diploma.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeskReserveDto {
    private long id;
    private int numberOfSeats;
    private String status;
    private long institutionId;
    private LocalDateTime dateOfReserve;
}
