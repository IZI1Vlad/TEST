package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.Raiting;
import com.mcb.creditfactory.service.plane.AirPlaneService;
import com.mcb.creditfactory.service.raiting.RaitingService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CollateralService {
    private CarService carService;
    private AirPlaneService planeService;
    private RaitingService raitingService;

    public CollateralService(CarService carService, AirPlaneService planeService, RaitingService raitingService) {
        this.carService = carService;
        this.planeService = planeService;
        this.raitingService = raitingService;
    }

    public Long saveCollateral(Collateral object) {
        Raiting raiting;

        if (object instanceof CarDto) {

            CarDto carDto = (CarDto) object;
            boolean approved = carService.approve(carDto);

            if (!approved) {
                return null;
            }

            raiting = raitingService.save(carDto.getValue());

            return saveCarEntityAndGetId(raiting, carDto);

        }

        else if (object instanceof AirPlaneDto) {


            AirPlaneDto planeDto = (AirPlaneDto) object;
            boolean approved = planeService.approve(planeDto);
            if (!approved) {
                return null;
            }

            raiting = raitingService.save(planeDto.getValue());

            return saveAirplaneEntityAndGetId(raiting, planeDto);

        } else throw new IllegalArgumentException();

    }

    private Long saveAirplaneEntityAndGetId(Raiting assessment, AirPlaneDto airplaneDto) {
        return Optional.of(airplaneDto)
                .map(planeService::fromDto)
                .map(airplane -> {
                    Long id = airplane.getId();
                    List<Raiting> list = new ArrayList<>();

                    if (id != null) {
                        list = planeService.load(id).get().getAssessments();
                    }

                    list.add(assessment);
                    airplane.setAssessments(list);

                    return planeService.save(airplane);
                })
                .map(planeService::getId)
                .orElse(null);
    }

    private Long saveCarEntityAndGetId(Raiting assessment, CarDto carDto) {
        return Optional.of(carDto)
                .map(carService::fromDto)
                .map(car -> {
                    Long id = car.getId();
                    List<Raiting> list = new ArrayList<>();

                    if (id != null) {
                        list = carService.load(id).get().getAssessments();
                    }

                    list.add(assessment);
                    car.setAssessments(list);

                    return carService.save(car);
                })
                .map(carService::getId)
                .orElse(null);
    }

    public Collateral getInfo(Collateral object) {
        if (object instanceof CarDto) {

            return Optional.of((CarDto) object)
                    .flatMap(carDto -> carService.load(carDto.getId()))
                    .map(carService::toDTO)
                    .orElse(null);

        } else if (object instanceof AirPlaneDto) {

            return Optional.of((AirPlaneDto) object)
                    .flatMap(airplaneDto -> planeService.load(airplaneDto.getId()))
                    .map(planeService::toDTO)
                    .orElse(null);

        } else throw new IllegalArgumentException();
    }

}
