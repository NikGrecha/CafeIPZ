package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DishOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "user_worker_id")
    private long userWorkerId;
    @Column(name = "institution_id")
    private long institutionId;
    @Column(name = "dish_id")
    private long dishId;
    @Column(name = "count_dish")
    private int countDish;
}
