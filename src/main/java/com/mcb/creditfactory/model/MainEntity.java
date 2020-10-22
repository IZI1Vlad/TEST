package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.FIELD)
public class MainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;

    @Column(name = "year_issue")
    private Short year;

    public MainEntity(Long id, String brand, String model, Short year) {
        this(id, brand, model, year, Collections.emptyList());
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Raiting> assessments;

}
