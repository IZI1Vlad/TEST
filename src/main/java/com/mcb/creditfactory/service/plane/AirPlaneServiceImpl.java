package com.mcb.creditfactory.service.plane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.repository.AirPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirPlaneServiceImpl implements AirPlaneService {

    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AirPlaneRepository airplaneRepository;

    @Override
    public boolean approve(AirPlaneDto dto) {
        return approveService.approve(new AirPlaneAdapter(dto)) == 0;
    }

    @Override
    public AirPlane save(AirPlane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public Optional<AirPlane> load(Long id) {
        return airplaneRepository.findById(id);
    }

    @Override
    public AirPlane fromDto(AirPlaneDto dto) {
        return new AirPlane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getManufacturer(),
                dto.getYear(),
                dto.getSize(),
                dto.getPassengers(),
                dto.getType(),
                dto.getRaitingList()
        );
    }

    @Override
    public AirPlaneDto toDTO(AirPlane airplane) {
        return new AirPlaneDto(
                airplane.getId(),
                airplane.getBrand(),
                airplane.getModel(),
                airplane.getManufacturer(),
                airplane.getYear(),
                airplane.getSize(),
                airplane.getPassengers(),
                airplane.getType(),
                airplane.getRaitingList()
        );
    }

    @Override
    public Long getId(AirPlane airplane) {
        return airplane.getId();
    }

}