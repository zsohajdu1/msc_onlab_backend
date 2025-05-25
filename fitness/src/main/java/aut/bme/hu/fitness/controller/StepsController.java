package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.StepsDTO;
import aut.bme.hu.fitness.service.StepsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/steps")
@RequiredArgsConstructor
public class StepsController {
    private final StepsService StepsService;

    @GetMapping("/date")
    public ResponseEntity<StepsDTO> getDateSteps(@RequestParam LocalDate date, @AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getSubject();
        return ResponseEntity.ok(StepsService.getDateSteps(date, userEmail));
    }

    @PutMapping("")
    public ResponseEntity<?> save(@RequestBody StepsDTO stepsDTO, @AuthenticationPrincipal Jwt jwt) {
        stepsDTO.setEmail(jwt.getSubject());
        StepsService.save(stepsDTO);
        return ResponseEntity.ok().build();
    }
}