package com.example.pruefungsrechner.Service;

import com.example.pruefungsrechner.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(User user, String token) {
        String confirmationUrl = "http://localhost:8080/auth/confirm?token=" + token;
        String message = "Bitte bestätige deine E-Mail-Adresse, indem du auf den folgenden Link klickst:\n" + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("E-Mail-Bestätigung");
        email.setText(message);

        mailSender.send(email);
    }
}