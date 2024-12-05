package aut.bme.hu.fitness.service.impl;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;
import aut.bme.hu.fitness.entity.CalorieIntake;
import aut.bme.hu.fitness.repository.CalorieIntakeRepository;
import aut.bme.hu.fitness.service.CalorieIntakeService;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public void save(CalorieIntakeDTO calorieIntakeDTO) {
        calorieIntakeRepository.save(convertToEntity(calorieIntakeDTO));
    }

    @Override
    public void delete(long id) {
        calorieIntakeRepository.deleteById(id);
    }

    @Override
    public List<CalorieIntakeDTO> getDateCalorieIntakes(LocalDate date, String uid) {
        List<CalorieIntake> calorieIntakes =
                calorieIntakeRepository.findAllByUidAndDate(uid, date);
        return calorieIntakes.stream().map(CalorieIntakeServiceImpl::convertToDTO).collect(Collectors.toList());
    }

    private static CalorieIntakeDTO convertToDTO(CalorieIntake calorieIntake) {
        CalorieIntakeDTO calorieIntakeDTO = new CalorieIntakeDTO();
        calorieIntakeDTO.setId(calorieIntake.getId());
        calorieIntakeDTO.setUid(calorieIntake.getUid());
        calorieIntakeDTO.setName(calorieIntake.getName());
        calorieIntakeDTO.setDate(calorieIntake.getDate());
        calorieIntakeDTO.setCalories(calorieIntake.getCalories());
        calorieIntakeDTO.setQuantity(calorieIntake.getQuantity());
        return calorieIntakeDTO;
    }

    private static CalorieIntake convertToEntity(CalorieIntakeDTO calorieIntakeDTO) {
        CalorieIntake calorieIntake = new CalorieIntake();
        calorieIntake.setId(calorieIntakeDTO.getId());
        calorieIntake.setName(calorieIntakeDTO.getName());
        calorieIntake.setDate(calorieIntakeDTO.getDate());
        calorieIntake.setCalories(calorieIntakeDTO.getCalories());
        calorieIntake.setQuantity(calorieIntakeDTO.getQuantity());
        return calorieIntake;
    }

}
