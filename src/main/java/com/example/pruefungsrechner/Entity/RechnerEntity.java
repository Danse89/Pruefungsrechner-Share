package com.example.pruefungsrechner.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RechnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double punktzahl1;
    private double punktzahl2;
    private double punktzahl3;
    private double punktzahl4;
    private double punktzahl5;
    private double punktzahl6;
    private double endnote;
    private boolean bestanden;

//    public RechnerEntity(double punktzahl1, double punktzahl2, double punktzahl3, double punktzahl4, double punktzahl5, double punktzahl6, double endnote, boolean bestanden) {
//        this.punktzahl1 = punktzahl1;
//        this.punktzahl2 = punktzahl2;
//        this.punktzahl3 = punktzahl3;
//        this.punktzahl4 = punktzahl4;
//        this.punktzahl5 = punktzahl5;
//        this.punktzahl6 = punktzahl6;
//        this.endnote = endnote;
//        this.bestanden = bestanden;
//    }
}
