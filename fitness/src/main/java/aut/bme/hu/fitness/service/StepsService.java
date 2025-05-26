package aut.bme.hu.fitness.service;

import aut.bme.hu.fitness.dto.StepsDTO;

import java.time.LocalDate;

public interface StepsService {
    StepsDTO getDateSteps(LocalDate date, String userEmail);

    void save(StepsDTO stepsDTO);
}
