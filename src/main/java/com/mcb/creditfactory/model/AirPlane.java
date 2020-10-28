package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AIRPLANE")
public class AirPlane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String manufacturer;

    @Column(name = "year_of_issue")
    private Short year;

    @Column(name = "fuel_capacity")
    private Integer size;
    private Integer passengers;
    private String type;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "airplane_raiting",
            joinColumns = {@JoinColumn(name = "airplane_id", nullable = true)},
            inverseJoinColumns = {@JoinColumn(name = "raiting_id")})
    private List<Raiting> raitingList;
}
