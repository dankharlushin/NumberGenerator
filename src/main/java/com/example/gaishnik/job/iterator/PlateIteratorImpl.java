package com.example.gaishnik.job.iterator;

import com.example.gaishnik.exception.NoFreePlatesException;
import com.example.gaishnik.model.Plate;
import com.example.gaishnik.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PlateIteratorImpl implements PlateIterator {

    private static final List<Character> lettersContainer = Arrays.asList(
            'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'
    );
    private static Integer count = 0;

    private Plate plate = new Plate(null, 0, "ААА", false);

    private PlateRepository plateRepository;

    @Autowired
    public void setPlateRepository(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    @Override
    public boolean hasNext() {
        Plate lastPlate = plateRepository.findFirstByOrderByIdDesc();
        if (lastPlate == null) {
            return true;
        }
        return lastPlate.getId() < Math.pow(lettersContainer.size(), 3) * 1000;
    }

    @Override
    public Plate getNext() throws NoFreePlatesException {
        if (!hasNext()) {
            throw new NoFreePlatesException("No free plates");
        }
        else {
            if (count == 0) {
                count++;
                return this.plate;
            }
            else {
                if (this.plate.getNumbers() < 999) {
                    this.plate.setNumbers(this.plate.getNumbers() + 1);
                }
                else {
                    this.plate.setNumbers(0);

                    char lowChar = this.plate.getLetters().charAt(2);
                    char midChar = this.plate.getLetters().charAt(1);
                    char highChar = this.plate.getLetters().charAt(0);

                    if (lowChar != lettersContainer.get(lettersContainer.size() - 1)) {
                        lowChar = lettersContainer.get(lettersContainer.indexOf(lowChar) + 1);
                        this.plate.setLetters(String.valueOf(highChar)
                                + midChar
                                + lowChar);
                    }
                    else if (midChar != lettersContainer.get(lettersContainer.size() - 1)) {
                        midChar = lettersContainer.get(lettersContainer.indexOf(midChar) + 1);
                        lowChar = lettersContainer.get(0);
                        this.plate.setLetters(String.valueOf(highChar)
                                + midChar
                                + lowChar);
                    }
                    else {
                        highChar = lettersContainer.get(lettersContainer.indexOf(highChar) + 1);
                        midChar = lettersContainer.get(0);
                        lowChar = lettersContainer.get(0);
                        this.plate.setLetters(String.valueOf(highChar)
                                + midChar
                                + lowChar);
                    }

                }
            }
        }

        return this.plate;
    }

}
