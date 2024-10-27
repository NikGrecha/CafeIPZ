package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "user_worker_id")
    private long userWorkerId;
    @Column(name = "institution_id")
    private long institutionId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_worker_id", referencedColumnName = "id", nullable = false)
//    private UserTable user;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
//    private Institution institution;
}
