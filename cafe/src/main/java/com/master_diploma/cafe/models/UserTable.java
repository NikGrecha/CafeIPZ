package com.master_diploma.cafe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_table_generator")
    @SequenceGenerator(name="user_table_generator", sequenceName = "user_table_id_seq", allocationSize=1)
    @Column(name = "id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private long phone;
    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;
    @ManyToMany
    @JoinTable(
            name = "user_institution", // Name of the existing join table
            joinColumns = @JoinColumn(name = "user_worker_id"), // Join column referencing 'Student'
            inverseJoinColumns = @JoinColumn(name = "institution_id") // Join column referencing 'Course'
    )
    private Set<Institution> institutions = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<Reserve> reserves;
    @OneToMany(mappedBy = "user")
    private Set<OrderTable> orders;
    @OneToMany(mappedBy = "user")
    private Set<DishOrder> dishOrders;
    @OneToMany(mappedBy = "user")
    private Set<FavoriteDish> favoriteDishes;
}
