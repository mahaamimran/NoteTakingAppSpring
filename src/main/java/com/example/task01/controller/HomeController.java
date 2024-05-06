package com.example.task01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "notes";  // This tells Spring Boot to return the 'notes.html' page when the root URL is accessed.
    }
}
