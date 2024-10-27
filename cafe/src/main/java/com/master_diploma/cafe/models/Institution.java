package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Institution {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "institution_generator")
    @SequenceGenerator(name="institution_generator", sequenceName = "institution_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "number_of_stars")
    private double numberOfStars;
    @Column(name = "time_open")
    private Time timeOpen;
    @Column(name = "time_close")
    private Time timeClose;

    @OneToMany(mappedBy = "institution")
    private Set<Review> reviews;
    @ManyToMany(mappedBy = "institutions")
    private Set<UserTable> employees = new HashSet<>();
    @OneToMany(mappedBy = "institution")
    private Set<Desk> desks;
    @OneToMany(mappedBy = "institution")
    private Set<Award> awards;
    @OneToMany(mappedBy = "institution")
    private Set<Dish> dishes;
    @OneToMany(mappedBy = "institution")
    private Set<StorageFacility> storageFacilities;
}
