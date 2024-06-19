package com.baziuk.SpringSecuredDemo.controllers;

import com.baziuk.SpringSecuredDemo.dto.AppError;
import com.baziuk.SpringSecuredDemo.dto.JwtAuthToken;
import com.baziuk.SpringSecuredDemo.dto.JwtRequest;
import com.baziuk.SpringSecuredDemo.dto.JwtResponse;
import com.baziuk.SpringSecuredDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private UserService userService;
    private JwtAuthToken jwtAuthToken;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException badCredentialsException) {
            return new  ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), "Невірний логін чи пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = new JwtAuthToken().generatedToken(userDetails);
        return ResponseEntity.ok().body(new JwtResponse(token));
    }
}
