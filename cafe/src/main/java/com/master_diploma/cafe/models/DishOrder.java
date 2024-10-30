package com.master_diploma.cafe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dish_order")
public class DishOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_order_generator")
    @SequenceGenerator(name="dish_order_generator", sequenceName = "dish_order_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "count_dish")
    private int countDish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_worker_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private UserTable userWorker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private OrderTable order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Dish dish;

}
