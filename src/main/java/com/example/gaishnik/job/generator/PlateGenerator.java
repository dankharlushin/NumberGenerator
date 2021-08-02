package com.example.gaishnik.job.generator;

import com.example.gaishnik.exception.NoFreePlatesException;
import com.example.gaishnik.model.Plate;

public interface PlateGenerator {

    Plate getRandom() throws NoFreePlatesException;

    boolean hasFree();
}
