package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mcb.creditfactory.model.Raiting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("plane")
public class AirPlaneDto implements Collateral {

    private Long id;
    private String brand;
    private String model;
    private String manufacturer;
    private Short year;
    private Integer size;
    private Integer passengers;
    private String type;
    private List<Raiting> raitingList;

}
