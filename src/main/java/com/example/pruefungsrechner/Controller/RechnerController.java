package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Entity.RechnerEntity;
import com.example.pruefungsrechner.Service.RechnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rechner")
public class RechnerController {
    @Autowired
    private  RechnerService service;


    @PostMapping("/calculate")
    public String resultatAusrechnen(
            @RequestParam("punktzahl1") double p1,
            @RequestParam("punktzahl2") double p2,
            @RequestParam("punktzahl3") double p3,
            @RequestParam("punktzahl4") double p4,
            @RequestParam("punktzahl5") double p5,
            @RequestParam("punktzahl6") double p6,
            Model model) {

        RechnerEntity ergebnis = service.berechneErgebnis(p1, p2, p3, p4, p5, p6);
        String result = ergebnis.isBestanden()
                ? ("Bestanden mit einer Endnote von: " + ergebnis.getEndnote() )
                : "Nicht bestanden (Mindestens eine Teilprüfung unter 50%)";

        model.addAttribute("result", result);
        return "Prüfungsrechner";
    }
}




