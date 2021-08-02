package com.example.gaishnik.repository;

import com.example.gaishnik.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlateRepository extends JpaRepository<Plate, Integer> {

    Plate getPlateByNumbersAndLetters(Integer numbers, String letters);

    Plate findFirstByOrderByIdDesc();

}
