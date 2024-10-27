package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_table_generator")
    @SequenceGenerator(name="order_table_generator", sequenceName = "orders_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "status")
    private String status;
    @Column(name = "wishes")
    private String wishes;
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;
    @Column(name = "closing_date")
    private LocalDateTime closingDate;
    @Column(name = "price")
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_client_id", referencedColumnName = "id", nullable = false)
    private UserTable user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_id", referencedColumnName = "id", nullable = false)
    private Desk desk;
    @OneToMany(mappedBy = "order")
    private Set<DishOrder> dishOrders;

    @PrePersist
    void preInsert() {
        if (status == null)
            status = "In the queue";
    }
}
