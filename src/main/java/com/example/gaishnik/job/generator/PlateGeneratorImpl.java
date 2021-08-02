package com.example.gaishnik.job.generator;

import com.example.gaishnik.exception.NoFreePlatesException;
import com.example.gaishnik.model.Plate;
import com.example.gaishnik.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class PlateGeneratorImpl implements PlateGenerator {

    private static final List<Character> lettersContainer = Arrays.asList(
            'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'
    );

    private Plate plate = new Plate(null, -1, "", true);

    private PlateRepository plateRepository;

    @Autowired
    public void setPlateRepository(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    @Override
    public boolean hasFree() {
        Plate lastPlate = plateRepository.findFirstByOrderByIdDesc();
        if (lastPlate == null) {
            return true;
        }
        return lastPlate.getId() < Math.pow(lettersContainer.size(), 3) * 1000;
    }

    @Override
    public Plate getRandom() throws NoFreePlatesException {
        if (!hasFree()) {
            throw new NoFreePlatesException("No free plates");
        }
        else {
            this.plate.setNumbers(getRandomNumbers());
            this.plate.setLetters(getRandomLetters());
        }
        return this.plate;
    }

    public static String getRandomLetters() {
        Random random = new Random();

        char lowChar = lettersContainer.get(random.nextInt(lettersContainer.size()));
        char midChar = lettersContainer.get(random.nextInt(lettersContainer.size()));
        char highChar = lettersContainer.get(random.nextInt(lettersContainer.size()));

        return String.valueOf(lowChar) + midChar + highChar;
    }

    public static Integer getRandomNumbers() {
        Random random = new Random();
        return random.nextInt(1001);
    }
}
