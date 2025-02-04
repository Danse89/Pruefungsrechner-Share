package com.example.pruefungsrechner.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeitenController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/startseite")
    public String startseite() {
        System.out.println();
        return "startseite";
    }

    @GetMapping("/Chatseite")
    public String Chatseite() {

        return "Chatseite";
    }

    @GetMapping("/Prüfungsrechner")
    public String Prüfungsrechner() {
        return "Prüfungsrechner";
    }
}

