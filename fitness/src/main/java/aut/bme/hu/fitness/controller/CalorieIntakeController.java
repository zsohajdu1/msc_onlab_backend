package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.CalorieIntakeDTO;
import aut.bme.hu.fitness.service.CalorieIntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/calorieintakes")
@RequiredArgsConstructor
public class CalorieIntakeController {
    private final CalorieIntakeService calorieIntakeService;

    @GetMapping("/date")
    public ResponseEntity<List<CalorieIntakeDTO>> getDateCalorieIntakes(
            @RequestParam LocalDate date,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userEmail = jwt.getSubject();
        return ResponseEntity.ok(calorieIntakeService.getDateCalorieIntakes(date, userEmail));
    }

    @PostMapping("")
    public ResponseEntity<?> create(
            @RequestBody CalorieIntakeDTO calorieIntakeDTO,
            @AuthenticationPrincipal Jwt jwt
    ) {
        calorieIntakeDTO.setEmail(jwt.getSubject());
        calorieIntakeService.save(calorieIntakeDTO);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping("")
    public ResponseEntity<?> save(
            @RequestBody CalorieIntakeDTO calorieIntakeDTO,
            @AuthenticationPrincipal Jwt jwt
    ) {
        calorieIntakeDTO.setEmail(jwt.getSubject());
        calorieIntakeService.save(calorieIntakeDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(
            @RequestParam long id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        calorieIntakeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}