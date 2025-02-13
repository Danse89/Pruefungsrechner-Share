package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Entity.RechnerEntity;
import com.example.pruefungsrechner.Service.PruefungsrechnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pruefungsrechner")
public class PruefungsrechnerController {

    @Autowired
    private PruefungsrechnerService pruefungsrechnerService;

    @GetMapping()
    public String showPruefungsrechner() {
        return "pruefungsrechner";
    }

    @PostMapping("/calculate")
    public String resultatAusrechnen(
            @RequestParam("punktzahl1") double p1,
            @RequestParam("punktzahl2") double p2,
            @RequestParam("punktzahl3") double p3,
            @RequestParam("punktzahl4") double p4,
            @RequestParam("punktzahl5") double p5,
            @RequestParam("punktzahl6") double p6,
            Model model) {

        RechnerEntity ergebnis = pruefungsrechnerService.berechneErgebnis(p1, p2, p3, p4, p5, p6);
        String result = ergebnis.isBestanden()
                ? "Bestanden mit einer Endnote von: " + ergebnis.getEndnote() + " (IHK Note: " + ergebnis.getIhkNote() + ")"
                : "Nicht bestanden " + ergebnis.getEndnote() + " (IHK Note: " + ergebnis.getIhkNote() + ")";
        model.addAttribute("result", result);
        return "pruefungsrechner";
    }
}
