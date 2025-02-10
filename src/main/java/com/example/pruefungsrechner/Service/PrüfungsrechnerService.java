package com.example.pruefungsrechner.Service;


import com.example.pruefungsrechner.Entity.RechnerEntity;
import com.example.pruefungsrechner.Repository.RechnerRepository;
import org.springframework.stereotype.Service;

@Service
public class PrüfungsrechnerService {
    private final RechnerRepository repository;

    public PrüfungsrechnerService(RechnerRepository repository) {
        this.repository = repository;
    }

    public RechnerEntity berechneErgebnis(double p1, double p2, double p3, double p4, double p5, double p6) {
        double finalNote = (p1 * 0.20) + (p2 * 0.10) + (p3 * 0.10) + (p4 * 0.10) + (p5 * 0.25) + (p6 * 0.25);
        boolean failed = (p2 < 50 || p3 < 50 || p4 < 50 || p5 < 50 || p6 < 50); //TODO: herausfinden ob p5 und p6 eine note ist. Kann man dort auch durchfallen?

        RechnerEntity ergebnis = RechnerEntity.builder()
                .punktzahl1(p1)
                .punktzahl2(p2)
                .punktzahl3(p3)
                .punktzahl4(p4)
                .punktzahl5(p5)
                .punktzahl6(p6)
                .bestanden(!failed)
                .endnote(finalNote)
                .build();

        repository.save(ergebnis);

        return ergebnis;
    }
}

