package aut.bme.hu.fitness.dto;

import aut.bme.hu.fitness.entity.ActivityLevel;
import aut.bme.hu.fitness.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserProfileDTO {
    private Long id;

    private String email;

    private LocalDate birthDate;

    private Gender gender;

    private Integer height;

    private Integer weight;

    private ActivityLevel activityLevel;

    private Double tdee;

    private Boolean manualExercise;
}
