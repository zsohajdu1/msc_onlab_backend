package aut.bme.hu.fitness.service.impl;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;
import aut.bme.hu.fitness.entity.CalorieIntake;
import aut.bme.hu.fitness.repository.CalorieIntakeRepository;
import aut.bme.hu.fitness.repository.UserRepository;
import aut.bme.hu.fitness.service.CalorieIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalorieIntakeServiceImpl implements CalorieIntakeService {

    @Autowired
    private CalorieIntakeRepository calorieIntakeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CalorieIntakeDTO> getDailyCalorieIntakes() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        long userId = userRepository.findByUsername(userName).getId();
        List<CalorieIntake> calorieIntakes =
                calorieIntakeRepository.findAllByUserIdAndDate(userId, LocalDate.now());
        return calorieIntakes.stream().map(CalorieIntakeServiceImpl::convertToDTO).collect(Collectors.toList());
    }

    public List<CalorieIntakeDTO> getMonthlyCalorieIntakes() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        long userId = userRepository.findByUsername(userName).getId();
        List<CalorieIntake> calorieIntakes =
                calorieIntakeRepository.findAllByUserIdAndDateAfter(userId, LocalDate.now().withDayOfMonth(1));
        return calorieIntakes.stream().map(CalorieIntakeServiceImpl::convertToDTO).collect(Collectors.toList());
    }

    public void save(CalorieIntakeDTO calorieIntakeDTO) {
        calorieIntakeRepository.save(convertToEntity(calorieIntakeDTO));
    }

    @Override
    public void delete(long id) {
        calorieIntakeRepository.deleteById(id);
    }

    private static CalorieIntakeDTO convertToDTO (CalorieIntake calorieIntake) {
        CalorieIntakeDTO calorieIntakeDTO = new CalorieIntakeDTO();
        calorieIntakeDTO.setId(calorieIntake.getId());
        calorieIntakeDTO.setUserId(calorieIntake.getUser().getId());
        calorieIntakeDTO.setName(calorieIntake.getName());
        calorieIntakeDTO.setDate(calorieIntake.getDate());
        calorieIntakeDTO.setCalories(calorieIntake.getCalories());
        calorieIntakeDTO.setQuantity(calorieIntake.getQuantity());
        return calorieIntakeDTO;
    }

    private static CalorieIntake convertToEntity (CalorieIntakeDTO calorieIntakeDTO) {
        CalorieIntake calorieIntake = new CalorieIntake();
        calorieIntake.setId(calorieIntakeDTO.getId());
        calorieIntake.setName(calorieIntakeDTO.getName());
        calorieIntake.setDate(calorieIntakeDTO.getDate());
        calorieIntake.setCalories(calorieIntakeDTO.getCalories());
        calorieIntake.setQuantity(calorieIntakeDTO.getQuantity());
        return calorieIntake;
    }

}
