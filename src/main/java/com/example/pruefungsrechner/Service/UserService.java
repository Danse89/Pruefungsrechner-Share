package com.example.pruefungsrechner.Service;

import com.example.pruefungsrechner.Entity.User;
import com.example.pruefungsrechner.Entity.VerificationToken;
import com.example.pruefungsrechner.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenService tokenService;
    @Autowired
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public void registerUser(User user) {
        user.setEnabled(false); // Noch nicht aktiviert
        userRepository.save(user);

        // Token erzeugen und E-Mail senden
        VerificationToken token = tokenService.createVerificationToken(user);
        emailService.sendVerificationEmail(user, token.getToken());
    }
}