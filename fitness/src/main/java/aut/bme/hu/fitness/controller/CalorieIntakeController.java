package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;
import aut.bme.hu.fitness.service.CalorieIntakeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calorieintakes")
public class CalorieIntakeController {
    private final CalorieIntakeService calorieIntakeService;

    public CalorieIntakeController(CalorieIntakeService calorieIntakeService) {
        this.calorieIntakeService = calorieIntakeService;
    }

    @GetMapping("/date")
    public List<CalorieIntakeDTO> getDateCalorieIntakes(@RequestParam LocalDate date, @RequestParam String uid) {
        return calorieIntakeService.getDateCalorieIntakes(date, uid);
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
    public void delete(@RequestParam long id) {
        calorieIntakeService.delete(id);
    }

}
