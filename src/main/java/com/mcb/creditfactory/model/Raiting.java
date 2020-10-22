package com.mcb.creditfactory.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAITING")
@ToString
public class Raiting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "raiting value")
    private BigDecimal raiting;

    @Column(name = "raiting date")
    private LocalDate localDate = LocalDate.now();

    public Raiting(BigDecimal raiting) {
        this.raiting = raiting;
    }
}
