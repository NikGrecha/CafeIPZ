package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserve_generator")
    @SequenceGenerator(name="reserve_generator", sequenceName = "reserve_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "date_of_creation")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateOfCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_id", referencedColumnName = "id", nullable = false)
    private Desk desk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_client_id", referencedColumnName = "id", nullable = false)
    private UserTable user;
    @OneToMany(mappedBy = "reserve")
    private Set<OrderTable> orders;
}