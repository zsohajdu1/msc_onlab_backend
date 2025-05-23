package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.UserProfileDTO;
import aut.bme.hu.fitness.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("")
    public ResponseEntity<UserProfileDTO> get(@RequestParam String uid) {
        return ResponseEntity.ok(userProfileService.get(uid));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(@RequestParam String uid) {
        return ResponseEntity.ok(userProfileService.get(uid) != null);
    }

    @PutMapping("")
    public ResponseEntity<?> save(@RequestBody UserProfileDTO userProfileDTO) {
        userProfileService.save(userProfileDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody UserProfileDTO userProfileDTO) {
        userProfileService.save(userProfileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
