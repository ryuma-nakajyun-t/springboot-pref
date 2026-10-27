package com.example.pref.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.pref.model.Pref;
import com.example.pref.service.PrefService;

@Controller
public class PerfController {

    private final PrefService prefService;

    public PerfController(PrefService prefService) {
        this.prefService = prefService;
    }

    @GetMapping("/index")
    public String hello(Model model) {

        model.addAttribute("message", "Hello Pref!");
        String filePath = System.getenv("DATA_DIR") + "/pref.csv";
        List<Pref> prefList = service.createPrefList(filePath);
        model.addAttribute("prefList", prefList);
        return "index";
    }
}
