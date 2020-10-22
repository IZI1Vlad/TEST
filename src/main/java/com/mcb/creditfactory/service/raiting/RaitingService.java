package com.mcb.creditfactory.service.raiting;

import com.mcb.creditfactory.model.Raiting;

import java.math.BigDecimal;
import java.util.List;

public interface RaitingService {
    Raiting save(Raiting raiting);

    Raiting getById(Long id);

    List<Raiting> getAll();

    void deleteById(Long id);

    Raiting save(BigDecimal value);
}
