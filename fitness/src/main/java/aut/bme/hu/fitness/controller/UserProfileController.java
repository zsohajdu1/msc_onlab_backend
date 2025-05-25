package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.UserProfileDTO;
import aut.bme.hu.fitness.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("")
    public ResponseEntity<UserProfileDTO> get(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getSubject();
        return ResponseEntity.ok(userProfileService.get(userEmail));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getSubject();
        return ResponseEntity.ok(userProfileService.get(userEmail) != null);
    }

    @PutMapping("")
    public ResponseEntity<?> save(
            @RequestBody UserProfileDTO userProfileDTO,
            @AuthenticationPrincipal Jwt jwt) {
        userProfileDTO.setEmail(jwt.getSubject());
        userProfileService.save(userProfileDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<?> create(
            @RequestBody UserProfileDTO userProfileDTO,
            @AuthenticationPrincipal Jwt jwt) {
        userProfileDTO.setEmail(jwt.getSubject());
        userProfileService.save(userProfileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
