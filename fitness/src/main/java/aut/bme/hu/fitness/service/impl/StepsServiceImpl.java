package aut.bme.hu.fitness.service.impl;

import aut.bme.hu.fitness.dto.StepsDTO;
import aut.bme.hu.fitness.entity.Steps;
import aut.bme.hu.fitness.repository.StepsRepository;
import aut.bme.hu.fitness.service.StepsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StepsServiceImpl implements StepsService {
    private final StepsRepository stepsRepository;

    private static StepsDTO convertToDTO(Steps steps) {
        return new StepsDTO(steps.getId(), steps.getEmail(), steps.getDate(), steps.getCount());
    }

    private static Steps convertToEntity(StepsDTO stepsDTO) {
        return new Steps(stepsDTO.getId(), stepsDTO.getEmail(), stepsDTO.getDate(), stepsDTO.getCount());
    }

    @Override
    public StepsDTO getDateSteps(LocalDate date, String userEmail) {
        Optional<Steps> steps = stepsRepository.findByEmailAndDate(userEmail, date);
        if (steps.isEmpty()) {
            Steps newSteps = new Steps(null, userEmail, date, 0);
            stepsRepository.save(newSteps);
            return convertToDTO(newSteps);
        } else
            return steps.map(StepsServiceImpl::convertToDTO).orElse(null);
    }

    @Override
    public void save(StepsDTO stepsDTO) {
        stepsRepository.save(convertToEntity(stepsDTO));
    }
}
