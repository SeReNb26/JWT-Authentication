package com.example.jwtauthentication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/endpoints")
public class EndPointsController {

    @GetMapping
    public String endPoints() {
        return "navigation/endpoints";
    }
}
