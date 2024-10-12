package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_table_generator")
    @SequenceGenerator(name="order_table_generator", sequenceName = "order_table_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "status")
    private boolean status;
    @Column(name = "wishes")
    private String wishes;
    @Column(name = "date_of_creation")
    private Date dateOfCreation;
    @Column(name = "closing_date")
    private Date closingDate;
//    @Column(name = "desk_id")
//    private long deskId;
//    @Column(name = "user_client_id")
//    private long userClientId;
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
