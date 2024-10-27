package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorite_dish")
public class FavoriteDish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favorite_dish_generator")
    @SequenceGenerator(name="favorite_dish_generator", sequenceName = "favorite_dish_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
//    @Column(name = "user_client_id")
//    private long userClientId;
//    @Column(name = "dish_id")
//    private long dishId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_client_id", referencedColumnName = "id", nullable = false)
    private UserTable user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", referencedColumnName = "id", nullable = false)
    private Dish dish;
}
