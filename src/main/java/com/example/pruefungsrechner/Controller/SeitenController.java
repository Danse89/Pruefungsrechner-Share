package com.example.pruefungsrechner.Controller;


import ch.qos.logback.core.model.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SeitenController {
    @GetMapping("/")
    public String login(){
        return "login";
    }




    @GetMapping("/startseite")
    public String startseite() {
        return "startseite";
    }
    @GetMapping("/Chatseite")
    public String Chatseite () {

        return"Chatseite";
    }
    @GetMapping("/Prüfungsrechner")
    public String Prüfungsrechner(){
        return "Prüfungsrechner";
    }
}

