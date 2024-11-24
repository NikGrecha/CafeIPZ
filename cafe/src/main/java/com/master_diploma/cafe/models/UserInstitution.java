package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_institution")
public class UserInstitution {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_institution_generator")
    @SequenceGenerator(name="user_institution_generator", sequenceName = "user_institution_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_worker_id", referencedColumnName = "id", nullable = false)
    private UserTable user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    private Institution institution;
    @OneToMany(mappedBy = "userInstitution")
    private Set<WorkSchedule> workSchedules;
}
