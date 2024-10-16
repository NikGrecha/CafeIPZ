package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calculation_generator")
    @SequenceGenerator(name="calculation_generator", sequenceName = "calculation_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;
    @Column(name = "ingredient_weight")
    private Long ingredientWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", referencedColumnName = "id", nullable = false)
    private Dish dish;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false)
    private Ingredient ingredient;
}
