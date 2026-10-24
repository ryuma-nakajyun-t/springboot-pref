package com.example.pref.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfController {

    @GetMapping("/hello")
    public String hello(Model model) {

        model.addAttribute("message", "Hello Spring Boot!");

        return "hello";
    }
}
