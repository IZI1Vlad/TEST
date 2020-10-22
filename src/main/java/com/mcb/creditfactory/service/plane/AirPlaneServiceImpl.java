package com.mcb.creditfactory.service.plane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.model.Raiting;
import com.mcb.creditfactory.repository.AirPlaneRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirPlaneService {
    private AirPlaneRepository repository;
    private ExternalApproveService approveService;


    public AirplaneServiceImpl(AirPlaneRepository repository, ExternalApproveService approveService) {
        this.repository = repository;
        this.approveService = approveService;
    }

    @Override
    public boolean approve(AirPlaneDto dto) {
        return approveService.approve(new AirPlaneAdapter(dto)) == 0;
    }

    @Override
    public AirPlane save(AirPlane plane) {
        return repository.save(plane);
    }

    @Override
    public Optional<AirPlane> load(Long id) {
        return repository.findById(id);
    }

    @Override
    public AirPlane fromDto(AirPlaneDto dto) {
        return new AirPlane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getSize(),
                dto.getPassengers(),
                dto.getType()
        );
    }

    @Override
    public AirPlaneDto toDTO(AirPlane plane) {
        return new AirPlaneDto(
                plane.getId(),
                plane.getBrand(),
                plane.getModel(),
                plane.getSize(),
                plane.getPassengers(),
                plane.getType(),
                plane.getYear(),
                getLastAssessmentValue(plane)
        );
    }

    @Override
    public Long getId(AirPlane airplane) {
        return airplane.getId();
    }

    private BigDecimal getLastAssessmentValue(AirPlane plane) {
        return plane.getRaiting().stream()
                .max(Comparator.comparing(Raiting::getLocalDate))
                .get().getRaiting();
    }
}