package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_schedule_generator")
    @SequenceGenerator(name="work_schedule_generator", sequenceName = "work_schedule_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_time_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeStart;
    @Column(name = "date_time_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTimeEnd;
    @Column(name = "hours_of_work")
    private LocalTime hoursOfWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_institution_id", referencedColumnName = "id", nullable = false)
    private UserInstitution userInstitution;

    @PrePersist
    void preInsert() {
        if (hoursOfWork == null)
            hoursOfWork = LocalTime.MIN;
    }
}
