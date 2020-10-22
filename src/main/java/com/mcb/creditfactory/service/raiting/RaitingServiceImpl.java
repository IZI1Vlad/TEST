package com.mcb.creditfactory.service.raiting;

import com.mcb.creditfactory.model.Raiting;
import com.mcb.creditfactory.repository.RaitingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RaitingServiceImpl implements RaitingService {
    private RaitingRepository repository;

    public RaitingServiceImpl(RaitingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Raiting save(Raiting raiting) {
        return repository.save(raiting);
    }

    @Override
    public Raiting getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Raiting> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Raiting save(BigDecimal value) {
        return repository.save(new Raiting(value));
    }
}
