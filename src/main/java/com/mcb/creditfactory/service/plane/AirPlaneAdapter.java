package com.mcb.creditfactory.service.plane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor

public class AirPlaneAdapter implements CollateralObject {
    private AirPlaneDto plane;

    @Override
    public BigDecimal getValue() {
        return plane.getValue();
    }

    @Override
    public Short getYear() {
        return plane.getYear();
    }

    @Override
    public LocalDate getDate() {
        // Для автомобилей дата оценки не используется, поэтому всегда берем текущую
        return LocalDate.now();
    }

    @Override
    public CollateralType getType() { return CollateralType.AIRPLANE;
    }
}
