package com.example.gaishnik.service;

import com.example.gaishnik.model.Plate;
import org.springframework.stereotype.Service;

@Service
public interface PlateService {
    Plate getRandomPlate();
    Plate getNextPlate();
    boolean isFree(Plate plate);
}
