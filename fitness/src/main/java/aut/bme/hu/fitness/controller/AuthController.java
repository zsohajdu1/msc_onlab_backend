package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.dto.UserRequestDTO;
import aut.bme.hu.fitness.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRequestDTO userRequest) {
        userService.registerUser(userRequest.getUsername(), userRequest.getPassword());
    }

    @PostMapping("/login")
    public void loginUser(@RequestBody UserRequestDTO userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @PostMapping("/logout")
    public void logoutUser() {
        SecurityContextHolder.clearContext();
    }


}

