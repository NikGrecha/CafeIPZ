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
@Table(name = "storage_facility")
public class StorageFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_facility_generator")
    @SequenceGenerator(name="storage_facility_generator", sequenceName = "storage_facility_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;


    @OneToMany(mappedBy = "storageFacility")
    private Set<Ingredient> ingredients;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    private Institution institution;
}
