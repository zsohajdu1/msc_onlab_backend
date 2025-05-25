package aut.bme.hu.fitness.service.impl;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;
import aut.bme.hu.fitness.entity.CalorieIntake;
import aut.bme.hu.fitness.repository.CalorieIntakeRepository;
import aut.bme.hu.fitness.service.CalorieIntakeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalorieIntakeServiceImpl implements CalorieIntakeService {

    private final CalorieIntakeRepository calorieIntakeRepository;

    public CalorieIntakeServiceImpl(CalorieIntakeRepository calorieIntakeRepository) {
        this.calorieIntakeRepository = calorieIntakeRepository;
    }

    private static CalorieIntakeDTO convertToDTO(CalorieIntake calorieIntake) {
        return new CalorieIntakeDTO(
                calorieIntake.getId(),
                calorieIntake.getEmail(),
                calorieIntake.getDate(),
                calorieIntake.getName(),
                calorieIntake.getCalories(),
                calorieIntake.getQuantity()
        );
    }

    private static List<CalorieIntakeDTO> convertToDTO(List<CalorieIntake> calorieIntakes) {
        return calorieIntakes.stream().map(CalorieIntakeServiceImpl::convertToDTO).collect(Collectors.toList());
    }

    private static CalorieIntake convertToEntity(CalorieIntakeDTO calorieIntakeDTO) {
        return new CalorieIntake(
                calorieIntakeDTO.getId(),
                calorieIntakeDTO.getEmail(),
                calorieIntakeDTO.getDate(),
                calorieIntakeDTO.getName(),
                calorieIntakeDTO.getCalories(),
                calorieIntakeDTO.getQuantity()
        );
    }

    public void save(CalorieIntakeDTO calorieIntakeDTO) {
        calorieIntakeRepository.save(convertToEntity(calorieIntakeDTO));
    }

    public void delete(long id) {
        calorieIntakeRepository.deleteById(id);
    }

    public List<CalorieIntakeDTO> getDateCalorieIntakes(LocalDate date, String uid) {
        return convertToDTO(calorieIntakeRepository.findAllByEmailAndDate(uid, date));
    }

}
