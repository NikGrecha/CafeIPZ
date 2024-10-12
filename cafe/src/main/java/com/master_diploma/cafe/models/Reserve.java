package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @Column(name = "date_od_creation")
    private Date dateOfCreation;
//    @Column(name = "desk_id")
//    private long deskId;
//    @Column(name = "user_client_id")
//    private long userClientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_id", referencedColumnName = "id", nullable = false)
    private Desk desk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_client_id", referencedColumnName = "id", nullable = false)
    private UserTable user;
}