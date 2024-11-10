package com.master_diploma.cafe.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_worker_id", referencedColumnName = "id", nullable = false)
    private UserTable userWorker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private OrderTable order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", referencedColumnName = "id", nullable = false)
    private Dish dish;
    @OneToMany(mappedBy = "dishOrder")
    private Set<HistoryDishOrder> historyDishOrders = new HashSet<>();;
}
