package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_generator")
    @SequenceGenerator(name="privilege_generator", sequenceName = "privilege_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
//    @Column(name = "role_id")
//    private long roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;
}
