package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "desk_generator")
    @SequenceGenerator(name="desk_generator", sequenceName = "desk_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;
    @Column(name = "number_of_seats")
    private int numberOfSeats;
    @Column(name = "status")
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    private Institution institution;

    @PrePersist
    void preInsert() {
        status = true;
    }
}
