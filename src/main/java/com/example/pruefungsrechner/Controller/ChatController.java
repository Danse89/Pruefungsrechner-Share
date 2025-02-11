package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Form.MessageForm;
import com.example.pruefungsrechner.Service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/Chatseite")
    public String chat(Model model) {
        model.addAttribute("messages", chatMessageService.getAllChatMessages());
        return "Chatseite";  // Stelle sicher, dass der Name mit der Template-Datei übereinstimmt (z.B. chat.html oder Chatseite.html)
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute MessageForm message) {
        chatMessageService.createChatMessage(message);
        return "redirect:/Chatseite";
    }
}
