package com.baziuk.SpringSecuredDemo.controllers;

import com.baziuk.SpringSecuredDemo.service.UserService;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String page() {
        return "/html/create";
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @PostMapping(value = "/create")
    public String createUser(@RequestParam String name,
                             @RequestParam String password,
                             @RequestParam String email) {
        userService.create(name, password, email);
        return "html/response";
    }


}
