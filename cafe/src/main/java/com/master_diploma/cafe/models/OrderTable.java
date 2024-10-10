package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "desk_id")
    private long deskId;
    @Column(name = "user_client_id")
    private long userClientId;
    @Column(name = "price")
    private int price;
}
