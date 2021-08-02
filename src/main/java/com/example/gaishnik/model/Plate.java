package com.example.gaishnik.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plate {

    private final String region = "116 RUS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numbers;

    private String letters;

    private Boolean isRandom;


    @Override
    public String toString() {
        String formatted = String.format("%03d", this.numbers);
        return this.letters.charAt(0) + formatted + this.letters.charAt(1) + this.letters.charAt(2)
                + " " + this.region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plate plate = (Plate) o;
        return numbers.equals(plate.numbers) && letters.equals(plate.letters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers, letters);
    }
}
