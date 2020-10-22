package com.mcb.creditfactory.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("raiting")
public class RaitingDto {
    private Long id;
    private BigDecimal raiting;
}
