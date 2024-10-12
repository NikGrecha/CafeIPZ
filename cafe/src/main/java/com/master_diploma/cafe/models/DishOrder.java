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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_order_generator")
    @SequenceGenerator(name="dish_order_generator", sequenceName = "dish_order_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
//    @Column(name = "user_worker_id")
//    private long userWorkerId;
//    @Column(name = "order_id")
//    private long orderId;
//    @Column(name = "dish_id")
//    private long dishId;
    @Column(name = "count_dish")
    private int countDish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_worker_id", referencedColumnName = "id", nullable = false)
    private UserTable user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private OrderTable order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", referencedColumnName = "id", nullable = false)
    private Dish dish;

}
