package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_table_generator")
    @SequenceGenerator(name="order_table_generator", sequenceName = "orders_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "status")
    private boolean status;
    @Column(name = "wishes")
    private String wishes;
    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;
    @Column(name = "closing_date")
    private LocalDate closingDate;
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
}
