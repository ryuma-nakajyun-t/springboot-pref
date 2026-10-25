package com.example.pref.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfController {

    @GetMapping("/index")
    public String hello(Model model) {

        model.addAttribute("message", "Hello Pref!");
        
        return "index";
    }
}
