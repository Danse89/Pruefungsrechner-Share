package com.example.pruefungsrechner.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeitenController {

    @GetMapping("/startseite")
    public String startseite() {
        return "startseite";
    }
}

