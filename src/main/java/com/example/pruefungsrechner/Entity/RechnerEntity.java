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
}
