package com.example.gaishnik.service;

import com.example.gaishnik.exception.NoFreePlatesException;
import com.example.gaishnik.job.generator.PlateGenerator;
import com.example.gaishnik.job.iterator.PlateIterator;
import com.example.gaishnik.model.Plate;
import com.example.gaishnik.repository.PlateRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlateServiceImpl implements PlateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlateServiceImpl.class);

    private PlateRepository plateRepository;
    private PlateIterator plateIterator;
    private PlateGenerator plateGenerator;

    @Autowired
    public void setPlateRepository(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    @Autowired
    public void setPlateIterator(PlateIterator plateIterator) {
        this.plateIterator = plateIterator;
    }

    @Autowired
    public void setPlateGenerator(PlateGenerator plateGenerator) {
        this.plateGenerator = plateGenerator;
    }

    @Override
    public Plate getRandomPlate() {
        Plate plate = new Plate();
        try {
            Plate nextPlate = plateGenerator.getRandom();
            while (!isFree(nextPlate)) {
                nextPlate = plateGenerator.getRandom();
            }
            plate.setNumbers(nextPlate.getNumbers());
            plate.setLetters(nextPlate.getLetters());
            plate.setIsRandom(nextPlate.getIsRandom());
        }
        catch (NoFreePlatesException e) {
            LOGGER.error("No free plates!");
        }

        plateRepository.save(plate);
        LOGGER.info("Plate " + plate + " was successfully issued");
        return plate;
    }

    @Override
    public Plate getNextPlate() {
        Plate plate = new Plate();
        try {
            Plate nextPlate = plateIterator.getNext();
            while (!isFree(nextPlate)) {
                nextPlate = plateIterator.getNext();
            }
            plate.setNumbers(nextPlate.getNumbers());
            plate.setLetters(nextPlate.getLetters());
            plate.setIsRandom(nextPlate.getIsRandom());
        }
        catch (NoFreePlatesException e) {
            LOGGER.error("No free plates!");
        }

        plateRepository.save(plate);
        LOGGER.info("Plate " + plate + " was successfully issued");
        return plate;

    }

    @Override
    public boolean isFree(Plate plate) {
        Plate p = plateRepository.getPlateByNumbersAndLetters(plate.getNumbers(), plate.getLetters());
        return !plate.equals(p);
    }


}
