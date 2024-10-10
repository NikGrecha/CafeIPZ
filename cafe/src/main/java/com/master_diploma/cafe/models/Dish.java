package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.reactive.GenericReactiveTransaction;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "ingredients")
    private String ingredients;
    @Column(name = "descriptions")
    private String descriptions;
    @Column(name = "recipe")
    private String recipe;
    @Column(name = "institution_id")
    private long institutionId;
}
