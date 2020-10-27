package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mcb.creditfactory.model.Raiting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("car")
public class CarDto implements Collateral {
    private Long id;
    private String brand;
    private String model;
    private Double power;
    private Short year;
    private List<Raiting> raitingListList;
}