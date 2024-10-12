package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    @SequenceGenerator(name="review_generator", sequenceName = "review_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
//    @Column(name = "user_client_id")
//    private long userClientId;
    @Column(name = "stars_food")
    private double starsFood;
    @Column(name = "stars_service")
    private double starsService;
    @Column(name = "stars_atmosphere")
    private double starsAtmosphere;
    @Column(name = "description")
    private String description;
    @Column(name = "date_of_creation")
    private Date dateOfCreation;
//    @Column(name = "institution_id")
//    private long institutionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_client_id", referencedColumnName = "id", nullable = false)
    private UserTable user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    private Institution institution;
}
