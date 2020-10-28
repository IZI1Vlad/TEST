package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.Raiting;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class CarAdapter implements CollateralObject {
    private CarDto car;

    @Override
    public BigDecimal getValue() {
        List<Raiting> raitings = car.getRaitingList();
        return raitings != null ? raitings.stream()
                .max(Comparator.comparing(Raiting::getDate))
                .map(Raiting::getValue)
                .orElse(null)
                : null;
    }

    @Override
    public Short getYear() {
        return car.getYear();
    }

    @Override
    public LocalDate getDate() {
        List<Raiting> raitings = car.getRaitingList();
        return raitings != null ? raitings.stream()
                .max(Comparator.comparing(Raiting::getDate))
                .map(Raiting::getDate)
                .orElse(null)
                : null;
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }
}