package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Entity.User;
import com.example.pruefungsrechner.Entity.VerificationToken;
import com.example.pruefungsrechner.Repository.UserRepository;
import com.example.pruefungsrechner.Service.RegistrationRequest;
import com.example.pruefungsrechner.Service.UserService;
import com.example.pruefungsrechner.Service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    /**
     * Registrierung eines neuen Nutzers und Versand der Bestätigungsmail
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
        User newUser = new User();
        newUser.setEmail(request.getMail());
        newUser.setPassword(request.getPassword()); // Passwort sollte mit BCrypt verschlüsselt werden!

        try {
            userService.registerUser(newUser);
            return ResponseEntity.ok("Registrierung erfolgreich! Bitte bestätige deine E-Mail.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fehler: " + e.getMessage());
        }
    }

    /**
     * Bestätigung der E-Mail über den per Mail gesendeten Token
     */
    @GetMapping("/confirm")
    public ResponseEntity<String> confirmEmail(@RequestParam("token") String token) {
        Optional<VerificationToken> verificationToken = tokenService.getToken(token);

        if (verificationToken.isEmpty() || verificationToken.get().isExpired()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ungültiges oder abgelaufenes Token");
        }

        // Benutzer aktivieren
        User user = verificationToken.get().getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok("E-Mail erfolgreich bestätigt! Du kannst dich nun einloggen.");
    }
}
