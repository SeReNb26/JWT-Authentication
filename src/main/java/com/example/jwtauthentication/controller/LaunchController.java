package com.example.jwtauthentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/launch")
public class LaunchController {
    @GetMapping
    public String launch() {
        return "navigation/launch";
    }
}
