package com.example.pruefungsrechner.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "customer") // Name der Tabelle in der DB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Inkrement für die ID
    private Long id;

    @Column(nullable = false)
    private String alias;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean is_verified;

    @Column(nullable = false)
    private String password;

}