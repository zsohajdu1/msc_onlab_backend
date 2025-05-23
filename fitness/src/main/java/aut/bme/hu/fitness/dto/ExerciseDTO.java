package aut.bme.hu.fitness.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExerciseDTO {
    private Long id;

    private String uid;

    private LocalDate date;

    private String name;

    private double calories;
}
