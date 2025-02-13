package com.example.pruefungsrechner.Service;

import com.example.pruefungsrechner.Entity.RechnerEntity;
import com.example.pruefungsrechner.Repository.RechnerRepository;
import org.springframework.stereotype.Service;

@Service
public class PruefungsrechnerService {
    private final RechnerRepository repository;

    public PruefungsrechnerService(RechnerRepository repository) {
        this.repository = repository;
    }

    public RechnerEntity berechneErgebnis(double p1, double p2, double p3, double p4, double p5, double p6) {
        double finalNote = (p1 * 0.20) + (p2 * 0.10) + (p3 * 0.10) + (p4 * 0.10) + (p5 * 0.25) + (p6 * 0.25);
        boolean failed = (p2 < 50 || p3 < 50 || p4 < 50 || p5 < 50 || p6 < 50);

        // Erzeuge den IHK-Notenschlüssel:
        String ihkGrade = getIhkGrade(finalNote, !failed);

        RechnerEntity ergebnis = RechnerEntity.builder()
                .punktzahl1(p1)
                .punktzahl2(p2)
                .punktzahl3(p3)
                .punktzahl4(p4)
                .punktzahl5(p5)
                .punktzahl6(p6)
                .bestanden(!failed)
                .endnote(finalNote)
                .ihkNote(ihkGrade)
                .build();

        repository.save(ergebnis);
        return ergebnis;
    }

    // Beispielhafter Mapping-Algorithmus: (Passen Sie die Grenzen nach Bedarf an)
    private String getIhkGrade(double finalNote, boolean passed) {
        if (!passed) {
            return "nicht bestanden";
        }
        if (finalNote >= 90) {
            return "1,0";
        } else if (finalNote >= 85) {
            return "1,3";
        } else if (finalNote >= 80) {
            return "1,7";
        } else if (finalNote >= 75) {
            return "2,0";
        } else if (finalNote >= 70) {
            return "2,3";
        } else if (finalNote >= 65) {
            return "2,7";
        } else if (finalNote >= 60) {
            return "3,0";
        } else if (finalNote >= 55) {
            return "3,3";
        } else if (finalNote >= 50) {
            return "3,7";
        } else {
            return "nicht bestanden";
        }
    }
}
