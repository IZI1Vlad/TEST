package com.mcb.creditfactory.service.plane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.Raiting;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class AirPlaneAdapter implements CollateralObject {

    private AirPlaneDto plane;

    @Override
    public BigDecimal getValue() {
        List<Raiting> raitings = plane.getRaitingList();
        return raitings != null ? raitings.stream()
                .max(Comparator.comparing(Raiting::getDate))
                .map(Raiting::getValue)
                .orElse(null)
                : null;
    }

    @Override
    public Short getYear() {
        return plane.getYear();
    }

    @Override
    public LocalDate getDate() {
        List<Raiting> raitings = plane.getRaitingList();
        return raitings != null ? raitings.stream()
                .max(Comparator.comparing(Raiting::getDate))
                .map(Raiting::getDate)
                .orElse(null)
                : null;
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }

}