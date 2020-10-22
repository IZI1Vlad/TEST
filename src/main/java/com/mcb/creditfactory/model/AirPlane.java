package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "AIRPLANE")
public class AirPlane extends MainEntity {

    @Column(name = "size")
    private String size;

    @Column(name = "passengers")
    private Integer passengers;

    @Column(name = "type")
    private Integer type;

    public AirPlane(Long id, String brand, String model, Short year, String size, Integer passengers, Integer type) {
        super(id, brand, model, year);
        this.size = size;
        this.passengers = passengers;
        this.type = type;
    }
}
