package aut.bme.hu.fitness.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void registerUser(String username, String password);
}
