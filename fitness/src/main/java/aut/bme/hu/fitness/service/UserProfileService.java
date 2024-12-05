package aut.bme.hu.fitness.service;

import aut.bme.hu.fitness.dto.UserProfileDTO;

public interface UserProfileService {
    UserProfileDTO get();

    void save(UserProfileDTO userProfileDTO);
}
