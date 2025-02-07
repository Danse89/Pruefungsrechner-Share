package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Entity.RechnerEntity;
import com.example.pruefungsrechner.Service.PrüfungsrechnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prüfungsrechner")
public class PrüfungsrechnerController {

    @Autowired
    private PrüfungsrechnerService prüfungsrechnerService;

    @GetMapping()
    public String Prüfungsrechner() {
        return "Prüfungsrechner";
    }

    @PostMapping("/calculate")
    public String resultatAusrechnen(
            @RequestParam("punktzahl1") double zwischenPrüfung, //TODO: Benamung anpassen
            @RequestParam("punktzahl2") double p2,
            @RequestParam("punktzahl3") double p3,
            @RequestParam("punktzahl4") double p4,
            @RequestParam("punktzahl5") double p5,
            @RequestParam("punktzahl6") double fachgespräch,
            Model model) {

        RechnerEntity ergebnis = prüfungsrechnerService.berechneErgebnis(zwischenPrüfung, p2, p3, p4, p5, fachgespräch);
        String result = ergebnis.isBestanden()
                ? ("Bestanden mit einer Endnote von: %s".formatted(ergebnis.getEndnote()))
                : "Nicht bestanden %s (Mindestens eine Teilprüfung unter 50%%)".formatted(ergebnis.getEndnote());

        model.addAttribute("result", result);
        return "Prüfungsrechner";
    }
}




