package com.example.pruefungsrechner.Service;

import com.example.pruefungsrechner.Entity.ChatMessage;
import com.example.pruefungsrechner.Form.MessageForm;
import com.example.pruefungsrechner.Repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private CustomerService customerService;

    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }

    public void createChatMessage(MessageForm message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .message(message.message())
                .sender(customerService.getCurrentUser())
                .created_at(LocalDateTime.now())
                .build();
        chatMessageRepository.save(chatMessage);
    }

}
