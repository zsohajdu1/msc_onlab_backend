package aut.bme.hu.fitness.service.impl;

import aut.bme.hu.fitness.dto.UserProfileDTO;
import aut.bme.hu.fitness.entity.ActivityLevel;
import aut.bme.hu.fitness.entity.UserProfile;
import aut.bme.hu.fitness.repository.UserProfileRepository;
import aut.bme.hu.fitness.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileDTO get(String email) {
        Optional<UserProfile> userProfile = userProfileRepository.findByEmail(email);
        return userProfile.map(this::convertToDTO).orElse(null);
    }

    public void save(UserProfileDTO userProfileDTO) {
        userProfileRepository.save(convertToEntity(userProfileDTO));
    }

    private UserProfileDTO convertToDTO(UserProfile userProfile) {
        return new UserProfileDTO(userProfile.getId(), userProfile.getEmail(), userProfile.getBirthDate(), userProfile.getGender(), userProfile.getHeight(), userProfile.getWeight(), userProfile.getActivityLevel(), calculateTdee(userProfile), userProfile.getManualExercise());
    }

    private UserProfile convertToEntity(UserProfileDTO userProfileDTO) {
        return new UserProfile(userProfileDTO.getId(), userProfileDTO.getEmail(), userProfileDTO.getBirthDate(), userProfileDTO.getGender(), userProfileDTO.getHeight(), userProfileDTO.getWeight(), userProfileDTO.getActivityLevel(), userProfileDTO.getManualExercise());
    }

    private double calculateTdee(UserProfile userProfile) {
        int age = Period.between(userProfile.getBirthDate(), LocalDate.now()).getYears();
        double value = 1;
        // Mifflin-St Jeor Equation:
        switch (userProfile.getGender()) {
            case Male -> value = 10 * userProfile.getWeight() + 6.25 * userProfile.getHeight() - 5 * age + 5;
            case Female -> value = 10 * userProfile.getWeight() + 6.25 * userProfile.getHeight() - 5 * age - 161;
        }
        if (userProfile.getManualExercise()) {
            value *= ActivityLevel.ActivityFactor(userProfile.getActivityLevel());
        }
        return value;
    }

}
