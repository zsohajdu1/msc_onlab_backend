package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;
import aut.bme.hu.fitness.service.CalorieIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calorieintakes")
public class CalorieIntakeController {
    @Autowired
    private CalorieIntakeService calorieIntakeService;

    @GetMapping("/daily")
    public List<CalorieIntakeDTO> getDailyCalorieIntakes() {
        return calorieIntakeService.getDailyCalorieIntakes();
    }

    @GetMapping("/monthly")
    public List<CalorieIntakeDTO> getMonthlyCalorieIntakes() {
        return calorieIntakeService.getMonthlyCalorieIntakes();
    }

    @PostMapping("")
    public void create(@RequestBody CalorieIntakeDTO calorieIntakeDTO) {
        calorieIntakeService.save(calorieIntakeDTO);
    }

    @PutMapping("")
    public void save(@RequestBody CalorieIntakeDTO calorieIntakeDTO) {
        calorieIntakeService.save(calorieIntakeDTO);
    }

    @DeleteMapping("")
    public void delete(@RequestBody long id) {
        calorieIntakeService.delete(id);
    }

}
