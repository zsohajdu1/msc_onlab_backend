package aut.bme.hu.fitness.service.impl;

import aut.bme.hu.fitness.dto.ExerciseDTO;
import aut.bme.hu.fitness.entity.Exercise;
import aut.bme.hu.fitness.repository.ExerciseRepository;
import aut.bme.hu.fitness.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository ExerciseRepository;

    private static ExerciseDTO convertToDTO(Exercise exercise) {
        return new ExerciseDTO(exercise.getId(), exercise.getEmail(), exercise.getDate(), exercise.getName(), exercise.getCalories());
    }

    private static List<ExerciseDTO> convertToDTO(List<Exercise> exercises) {
        return exercises.stream().map(ExerciseServiceImpl::convertToDTO).collect(Collectors.toList());
    }

    private static Exercise convertToEntity(ExerciseDTO exerciseDTO) {
        return new Exercise(exerciseDTO.getId(), exerciseDTO.getEmail(), exerciseDTO.getDate(), exerciseDTO.getName(), exerciseDTO.getCalories());
    }

    @Override
    public void save(ExerciseDTO exerciseDTO) {
        ExerciseRepository.save(convertToEntity(exerciseDTO));
    }

    @Override
    public void delete(long id) {
        ExerciseRepository.deleteById(id);
    }

    public List<ExerciseDTO> getDateExercises(LocalDate date, String uid) {
        return convertToDTO(ExerciseRepository.findAllByEmailAndDate(uid, date));
    }

}
