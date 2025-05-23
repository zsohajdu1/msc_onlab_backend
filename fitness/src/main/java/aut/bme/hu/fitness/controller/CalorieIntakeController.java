package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;
import aut.bme.hu.fitness.service.CalorieIntakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/calorieintakes")
public class CalorieIntakeController {
    private final CalorieIntakeService calorieIntakeService;

    public CalorieIntakeController(CalorieIntakeService calorieIntakeService) {
        this.calorieIntakeService = calorieIntakeService;
    }

    @GetMapping("/date")
    public ResponseEntity<List<CalorieIntakeDTO>> getDateCalorieIntakes(@RequestParam LocalDate date, @RequestParam String uid) {
        return ResponseEntity.ok(calorieIntakeService.getDateCalorieIntakes(date, uid));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CalorieIntakeDTO calorieIntakeDTO) {
        calorieIntakeService.save(calorieIntakeDTO);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping("")
    public ResponseEntity<?> save(@RequestBody CalorieIntakeDTO calorieIntakeDTO) {
        calorieIntakeService.save(calorieIntakeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam long id) {
        calorieIntakeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
