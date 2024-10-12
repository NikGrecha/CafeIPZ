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
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "award_generator")
    @SequenceGenerator(name="award_generator", sequenceName = "award_id_seq", allocationSize=1)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "date_of_receipt")
    private Date dateOfReceipt;
//    @Column(name = "institution_id")
//    private long institutionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    private Institution institution;
}
