package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("plane")
public class AirPlaneDto implements Collateral {
    private Long id;
    private String brand;
    private String model;
    private String size;
    private Integer passengers;
    private Integer type;
    private Short year;
    private BigDecimal value;
}
