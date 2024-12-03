package aut.bme.hu.fitness.controller;

import aut.bme.hu.fitness.entity.User;
import aut.bme.hu.fitness.service.UserService;
import aut.bme.hu.fitness.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public void registerUser(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
    }

    @PostMapping("/login")
    public void loginUser(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @PostMapping("/logout")
    public void logoutUser() {
        SecurityContextHolder.clearContext();
    }

}
