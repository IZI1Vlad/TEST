package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.car.CarService;
import com.mcb.creditfactory.service.plane.AirPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: reimplement this

@Service
public class CollateralService {
    @Autowired
    private CarService carService;
    private AirPlaneService planeService;

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object) {
        if (object instanceof CarDto) {

            CarDto car = (CarDto) object;
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

        else if (object instanceof AirPlaneDto) {


            AirPlaneDto plane = (AirPlaneDto) object;
            boolean approved = planeService.approve(plane);
            if (!approved) {
                return null;
            }

            return Optional.of(plane)
                    .map(planeService::fromDto)
                    .map(planeService::save)
                    .map(planeService::getId)
                    .orElse(null);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Collateral getInfo(Collateral object) {
        if   (!(object instanceof CarDto)) {

            CarDto car = (CarDto) object;
            boolean approved = carService.approve(car);
            if (!approved) {
                return null;
            }

            return Optional.of((CarDto) object)
                    .map(carService::fromDto)
                    .map(carService::getId)
                    .flatMap(carService::load)
                    .map(carService::toDTO)
                    .orElse(null);
        }

        else if (!(object instanceof AirPlaneDto)) {

            AirPlaneDto plane = (AirPlaneDto) object;
            boolean approved = planeService.approve(plane);
            if (!approved) {
                return null;
            }

            return Optional.of((AirPlaneDto) object)
                    .map(planeService::fromDto)
                    .map(planeService::getId)
                    .flatMap(planeService::load)
                    .map(planeService::toDTO)
                    .orElse(null);
            }
      else {
            throw new IllegalArgumentException();
        }
    }
}
