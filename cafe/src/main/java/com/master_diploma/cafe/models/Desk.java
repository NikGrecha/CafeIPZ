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
//    @Column(name = "institution_id")
//    private long institutionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    private Institution institution;
    @OneToMany(mappedBy = "desk")
    private Set<OrderTable> orders;
}
