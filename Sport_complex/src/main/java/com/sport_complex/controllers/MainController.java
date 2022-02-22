package com.sport_complex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("title","Sport Complex");
        return "main_page";
    }

    @GetMapping("/prices")
    public String pricesPage(Model model){
        model.addAttribute("title","Prices");
        return "prices";
    }
}
