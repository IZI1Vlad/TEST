package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.plane.AirPlaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollateralService {
    @Autowired
    private CarService carService;

    @Autowired
    private AirPlaneService airplaneService;

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object) {
        if ((object instanceof CarDto)) {
            CarDto car = (CarDto) object;
            return saveCar(car);
        } else if ((object instanceof AirPlaneDto)) {
            AirPlaneDto airplane = (AirPlaneDto) object;
            return saveAirplane(airplane);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Collateral getInfo(Collateral object) {
        if ((object instanceof CarDto)) {
            CarDto car = (CarDto) object;
            return getCarInfo(car);
        } else if ((object instanceof AirPlaneDto)) {
            AirPlaneDto plane = (AirPlaneDto) object;
            return getAirplaneInfo(plane);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Long saveCar(CarDto car) {
        boolean approved = carService.approve(car);
        if (!approved) {
            return null;
        }

        return Optional.of(car)
                .map(carService::fromDto)
                .map(carService::save)
                .map(carService::getId)
                .orElse(null);
    }

    private Long saveAirplane(AirPlaneDto plane) {
        boolean approved = airplaneService.approve(plane);
        if (!approved) {
            return null;
        }

        return Optional.of(plane)
                .map(airplaneService::fromDto)
                .map(airplaneService::save)
                .map(airplaneService::getId)
                .orElse(null);
    }

    private Collateral getCarInfo(CarDto car) {
        return Optional.of(car)
                .map(carService::fromDto)
                .map(carService::getId)
                .flatMap(carService::load)
                .map(carService::toDTO)
                .orElse(null);
    }

    private Collateral getAirplaneInfo(AirPlaneDto airplane) {
        return Optional.of(airplane)
                .map(airplaneService::fromDto)
                .map(airplaneService::getId)
                .flatMap(airplaneService::load)
                .map(airplaneService::toDTO)
                .orElse(null);
    }
}