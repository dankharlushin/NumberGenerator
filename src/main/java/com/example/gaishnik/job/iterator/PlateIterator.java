package com.example.gaishnik.job.iterator;

import com.example.gaishnik.exception.NoFreePlatesException;
import com.example.gaishnik.model.Plate;
import org.springframework.stereotype.Component;

@Component
public interface PlateIterator {

    boolean hasNext();

    Plate getNext() throws NoFreePlatesException;
}
