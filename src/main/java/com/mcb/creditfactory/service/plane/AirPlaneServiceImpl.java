package com.mcb.creditfactory.service.plane;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.AirPlane;
import com.mcb.creditfactory.repository.AirPlaneRepository;
import com.mcb.creditfactory.service.plane.AirPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirPlaneServiceImpl implements AirPlaneService {
    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private AirPlaneRepository planeRepository;

    @Override
    public boolean approve(AirPlaneDto dto) {
        return approveService.approve(new AirPlaneAdapter(dto)) == 0;
    }

    @Override
    public AirPlane save(AirPlane plane) {
        return planeRepository.save(plane);
    }

    @Override
    public Optional<AirPlane> load(Long id) {
        return planeRepository.findById(id);
    }

    @Override
    public AirPlane fromDto(AirPlaneDto dto) {
        return new AirPlane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getPower(),
                dto.getYear(),
                dto.getValue()
        );
    }

    @Override
    public AirPlaneDto toDTO(AirPlane plane) {
        return new AirPlaneDto(
                plane.getId(),
                plane.getBrand(),
                plane.getModel(),
                plane.getPower(),
                plane.getYear(),
                plane.getValue()
        );
    }

    @Override
    public Long getId(AirPlane plane) {
        return plane.getId();
    }
}
