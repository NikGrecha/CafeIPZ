package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

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
}
