package aut.bme.hu.fitness.service.impl;

import aut.bme.hu.fitness.dto.UserProfileDTO;
import aut.bme.hu.fitness.entity.ActivityLevel;
import aut.bme.hu.fitness.entity.Gender;
import aut.bme.hu.fitness.entity.UserProfile;
import aut.bme.hu.fitness.repository.UserProfileRepository;
import aut.bme.hu.fitness.repository.UserRepository;
import aut.bme.hu.fitness.service.UserProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final UserRepository userRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    public UserProfileDTO get() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        long userId = userRepository.findByUsername(userName).getId();
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        return userProfile.map(this::convertToDTO).orElse(null);
    }

    public void save(UserProfileDTO userProfileDTO) {
        userProfileRepository.save(convertToEntity(userProfileDTO));
    }

    private UserProfileDTO convertToDTO(UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(userProfile.getId());
        userProfileDTO.setUserId(userProfile.getUser().getId());
        userProfileDTO.setBirthDate(userProfile.getBirthDate());
        userProfileDTO.setGender(userProfile.getGender());
        userProfileDTO.setActivityLevel(userProfile.getActivityLevel());
        userProfileDTO.setHeight(userProfile.getHeight());
        userProfileDTO.setTdee(
                calculateTdee(userProfile.getWeight(),
                        userProfile.getHeight(),
                        userProfile.getBirthDate(),
                        userProfile.getGender(),
                        userProfile.getActivityLevel())
        );
        return userProfileDTO;
    }

    private UserProfile convertToEntity(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileDTO.getId());
        userProfile.setBirthDate(userProfileDTO.getBirthDate());
        userProfile.setGender(userProfileDTO.getGender());
        userProfile.setActivityLevel(userProfileDTO.getActivityLevel());
        userProfile.setHeight(userProfileDTO.getHeight());
        return userProfile;
    }

    private double calculateTdee(int weight, int height, LocalDate birthDate, Gender gender, ActivityLevel activityLevel) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        double value = 1;
        // Mifflin-St Jeor Equation:
        switch (gender) {
            case Male -> value = 10 * weight + 6.25 * height - 5 * age + 5;
            case Female -> value = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        return value * ActivityLevel.ActivityFactor(activityLevel);
    }

}
