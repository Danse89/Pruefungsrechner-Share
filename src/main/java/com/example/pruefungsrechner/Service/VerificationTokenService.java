package com.example.pruefungsrechner.Service;

import com.example.pruefungsrechner.Entity.User;
import com.example.pruefungsrechner.Entity.VerificationToken;
import com.example.pruefungsrechner.Repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationToken createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24)); // 24 Stunden gültig

        return verificationTokenRepository.save(verificationToken);
    }

    public Optional<VerificationToken> getToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }
}
