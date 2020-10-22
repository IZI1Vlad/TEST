package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Raiting;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private ExternalApproveService approveService;
    private CarRepository carRepository;

    public CarServiceImpl(ExternalApproveService approveService, CarRepository carRepository) {
        this.approveService = approveService;
        this.carRepository = carRepository;
    }

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car fromDto(CarDto dto) {
        return new Car(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getPower()
        );
    }

    @Override
    public CarDto toDTO(Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYear(),
                getLastAssessmentValue(car)
        );
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

    private BigDecimal getLastAssessmentValue(Car car) {
        if (car.getId() != null) {
            return car.getAssessments().stream()
                    .max(Comparator.comparing(Raiting::getLocalDate))
                    .get().getRaiting();
        } else {
            return null;
        }
    }
}
