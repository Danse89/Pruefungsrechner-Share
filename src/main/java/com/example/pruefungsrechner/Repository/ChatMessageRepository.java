package com.example.pruefungsrechner.Repository;

import com.example.pruefungsrechner.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
}