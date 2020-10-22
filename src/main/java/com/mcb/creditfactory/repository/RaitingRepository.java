package com.mcb.creditfactory.repository;

import com.mcb.creditfactory.model.Raiting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaitingRepository extends JpaRepository<Raiting, Long> {
}
