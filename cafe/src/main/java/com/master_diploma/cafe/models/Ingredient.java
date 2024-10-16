package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_generator")
    @SequenceGenerator(name="ingredient_generator", sequenceName = "ingredient_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "available")
    private Long available;

    @OneToMany(mappedBy = "ingredient")
    private Set<Calculation> calculations;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_facility_id", referencedColumnName = "id", nullable = false)
    private StorageFacility storageFacility;
}
