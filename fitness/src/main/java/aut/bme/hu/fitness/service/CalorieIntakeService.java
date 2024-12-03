package aut.bme.hu.fitness.service;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;

import java.util.List;

public interface CalorieIntakeService {
    List<CalorieIntakeDTO> getDailyCalorieIntakes();
    List<CalorieIntakeDTO> getMonthlyCalorieIntakes();
    void save(CalorieIntakeDTO calorieIntakeDTO);
    void delete(long id);
}
