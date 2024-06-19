package com.baziuk.SpringSecuredDemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("hello/")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok().body("Hello user");
    }

    @GetMapping("admin/")
    public ResponseEntity<?> admin(){
        return ResponseEntity.ok().body("Hello admin");
    }

}
