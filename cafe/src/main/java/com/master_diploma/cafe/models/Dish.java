package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.reactive.GenericReactiveTransaction;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_generator")
    @SequenceGenerator(name="dish_generator", sequenceName = "dish_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "descriptions")
    private String descriptions;
    @Column(name = "recipe")
    private String recipe;
    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    private Institution institution;
    @OneToMany(mappedBy = "dish")
    private Set<DishOrder> dishOrders;
    @OneToMany(mappedBy = "dish")
    private Set<FavoriteDish> favoriteDishes;
    @ManyToMany
    @JoinTable(
            name = "dish_tag", // Name of the existing join table
            joinColumns = @JoinColumn(name = "dish_id"), // Join column referencing 'Student'
            inverseJoinColumns = @JoinColumn(name = "tag_id") // Join column referencing 'Course'
    )
    private Set<Tag> tags = new HashSet<>();
    @OneToMany(mappedBy = "dish")
    private Set<Calculation> calculations;
}
