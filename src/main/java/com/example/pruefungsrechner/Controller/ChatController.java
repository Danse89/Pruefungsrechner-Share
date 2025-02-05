package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Form.MessageForm;
import com.example.pruefungsrechner.Entity.ChatMessage;
import com.example.pruefungsrechner.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/chatseite")
    public String chat(Model model) {
        System.out.println(chatMessageRepository.findAll().get(0));
        model.addAttribute("messages", chatMessageRepository.findAll());
        return "Chatseite.html";  // Stelle sicher, dass der Name mit der Template-Datei übereinstimmt (z.B. chat.html oder Chatseite.html)
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute MessageForm message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .message(message.message())
                .sender("Gast")
                .created_at(LocalDateTime.now())
                .build();
        chatMessageRepository.save(chatMessage);
        return "redirect:/chatseite";
    }
}
