package com.example.pruefungsrechner.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    // Hinzugefügtes Feld für den Absender
    private String sender;

    // Timestamp für die Erstellung der Nachricht
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;
}

