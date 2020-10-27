package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAITING")
public class Raiting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "raiting_value")
    private BigDecimal value;

    @Column(name = "raiting_date")
    private LocalDate date;


}
