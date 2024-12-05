package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.UserProfileDTO;
import aut.bme.hu.fitness.service.UserProfileService;
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
    public ResponseEntity<UserProfileDTO> get() {
        UserProfileDTO userProfileDTO = userProfileService.get();
        return ResponseEntity.ok(userProfileDTO);
    }

    @PutMapping("")
    public void save(@RequestBody UserProfileDTO userProfileDTO) {
        userProfileService.save(userProfileDTO);
    }

    @PostMapping("")
    public void create(@RequestBody UserProfileDTO userProfileDTO) {
        userProfileService.save(userProfileDTO);
    }
}
