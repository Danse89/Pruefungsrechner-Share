package com.example.pruefungsrechner.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, unique = true)
    private String alias;


    @Column(nullable = false)
    private boolean verified;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<ChatMessage> messages =new ArrayList<>();
}
