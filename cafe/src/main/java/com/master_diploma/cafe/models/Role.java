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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name="role_generator", sequenceName = "role_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserTable> users;
    @OneToMany(mappedBy = "role")
    private Set<Privilege> privileges;
}
