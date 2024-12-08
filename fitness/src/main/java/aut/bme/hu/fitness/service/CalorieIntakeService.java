package aut.bme.hu.fitness.service;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;

import java.time.LocalDate;
import java.util.List;

public interface CalorieIntakeService {
    void save(CalorieIntakeDTO calorieIntakeDTO);

    void delete(long id);

    List<CalorieIntakeDTO> getDateCalorieIntakes(LocalDate date, String uid);
}
