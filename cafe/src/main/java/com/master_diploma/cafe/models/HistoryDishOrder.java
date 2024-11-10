package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history_dish_order")
public class HistoryDishOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_dish_order_generator")
    @SequenceGenerator(name="history_dish_order_generator", sequenceName = "history_dish_order_id_seq", allocationSize=1)
    private long id;
    @Column(name = "status")
    private String status;
    @Column(name = "status_date")
    private LocalDateTime statusDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_order_id", referencedColumnName = "id", nullable = false)
    private DishOrder dishOrder;

    @PrePersist
    void preInsert() {
        if (status == null)
            status = "In the queue";
    }
}
