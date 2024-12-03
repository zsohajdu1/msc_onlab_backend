package aut.bme.hu.fitness.service.impl;

import aut.bme.hu.fitness.dto.UserProfileDTO;
import aut.bme.hu.fitness.entity.ActivityLevel;
import aut.bme.hu.fitness.entity.Gender;
import aut.bme.hu.fitness.entity.User;
import aut.bme.hu.fitness.entity.UserProfile;
import aut.bme.hu.fitness.repository.UserProfileRepository;
import aut.bme.hu.fitness.repository.UserRepository;
import aut.bme.hu.fitness.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserRepository userRepository;

    public UserProfileDTO get() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        long userId = userRepository.findByUsername(userName).getId();
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        if (userProfile.isPresent()) {
            return convertToDTO(userProfile.get());
        }
        else {
            return null;
        }
    }

    public void save(UserProfileDTO userProfileDTO) {
        userProfileRepository.save(convertToEntity(userProfileDTO));
    }

    private UserProfileDTO convertToDTO (UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(userProfile.getId());
        userProfileDTO.setUserId(userProfile.getUser().getId());
        userProfileDTO.setAge(userProfile.getAge());
        userProfileDTO.setGender(userProfile.getGender());
        userProfileDTO.setActivityLevel(userProfile.getActivityLevel());
        userProfileDTO.setHeight(userProfile.getHeight());
        userProfileDTO.setTdee(
                calculateTdee(userProfile.getWeight(),
                        userProfile.getHeight(),
                        userProfile.getAge(),
                        userProfile.getGender(),
                        userProfile.getActivityLevel())
        );
        return userProfileDTO;
    }

    private UserProfile convertToEntity (UserProfileDTO userProfileDTO) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileDTO.getId());
        userProfile.setAge(userProfileDTO.getAge());
        userProfile.setGender(userProfileDTO.getGender());
        userProfile.setActivityLevel(userProfileDTO.getActivityLevel());
        userProfile.setHeight(userProfileDTO.getHeight());
        return userProfile;
    }

    private double calculateTdee (int weight, int height, int age, Gender gender, ActivityLevel activityLevel) {
        double value = 1;
        // Mifflin-St Jeor Equation:
        switch (gender) {
            case Male -> value = 10 * weight + 6.25 * height - 5 * age + 5;
            case Female -> value = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        return value * ActivityLevel.ActivityFactor(activityLevel);
    }

}
