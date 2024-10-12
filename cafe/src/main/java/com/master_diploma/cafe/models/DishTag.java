package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Maybe this class should be deleted
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DishTag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_tag_generator")
    @SequenceGenerator(name="dish_tag_generator", sequenceName = "dish_tag_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "dish_id")
    private long dishId;
    @Column(name = "tag_id")
    private long tagId;
}
