package aut.bme.hu.fitness.dto;

import aut.bme.hu.fitness.entity.ActivityLevel;
import aut.bme.hu.fitness.entity.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserProfileDTO {
    private Long id;

    private String uid;

    private LocalDate birthDate;

    private Gender gender;

    private Integer height;

    private Integer weight;

    private ActivityLevel activityLevel;

    private Double tdee;
}
