package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.ExerciseDTO;
import aut.bme.hu.fitness.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping("/date")
    public ResponseEntity<List<ExerciseDTO>> getDateExercises(
            @RequestParam LocalDate date,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userEmail = jwt.getSubject();
        return ResponseEntity.ok(exerciseService.getDateExercises(date, userEmail));
    }

    @PostMapping("")
    public ResponseEntity<?> create(
            @RequestBody ExerciseDTO exerciseDTO,
            @AuthenticationPrincipal Jwt jwt
    ) {
        exerciseDTO.setEmail(jwt.getSubject());
        exerciseService.save(exerciseDTO);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping("")
    public ResponseEntity<?> save(
            @RequestBody ExerciseDTO exerciseDTO,
            @AuthenticationPrincipal Jwt jwt
    ) {
        exerciseDTO.setEmail(jwt.getSubject());
        exerciseService.save(exerciseDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(
            @RequestParam long id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        exerciseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}