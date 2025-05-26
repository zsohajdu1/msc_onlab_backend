package aut.bme.hu.fitness.service;

import aut.bme.hu.fitness.dto.AuthResponse;
import aut.bme.hu.fitness.dto.LoginRequest;
import aut.bme.hu.fitness.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    void validateToken(String token);
}
