package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "CAR")
@ToString(callSuper = true)
public class Car extends MainEntity {

    @Column(name = "power")
    private Double power;

    public Car(Long id, String brand, String model, Short year, Double power) {
        super(id, brand, model, year);
        this.power = power;
    }
}
