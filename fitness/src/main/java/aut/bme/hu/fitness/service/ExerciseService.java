package aut.bme.hu.fitness.service;

import aut.bme.hu.fitness.dto.ExerciseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseService {
    void save(ExerciseDTO exerciseDTO);

    void delete(long id);

    List<ExerciseDTO> getDateExercises(LocalDate date, String uid);
}
