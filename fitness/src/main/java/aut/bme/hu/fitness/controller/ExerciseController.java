package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.ExerciseDTO;
import aut.bme.hu.fitness.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/Exercises")
public class ExerciseController {
    private final ExerciseService ExerciseService;

    public ExerciseController(ExerciseService ExerciseService) {
        this.ExerciseService = ExerciseService;
    }

    @GetMapping("/date")
    public ResponseEntity<List<ExerciseDTO>> getDateExercises(@RequestParam LocalDate date, @RequestParam String uid) {
        return ResponseEntity.ok(ExerciseService.getDateExercises(date, uid));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ExerciseDTO ExerciseDTO) {
        ExerciseService.save(ExerciseDTO);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping("")
    public ResponseEntity<?> save(@RequestBody ExerciseDTO ExerciseDTO) {
        ExerciseService.save(ExerciseDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam long id) {
        ExerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
