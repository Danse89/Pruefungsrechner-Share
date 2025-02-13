package com.example.pruefungsrechner.Service;

import com.example.pruefungsrechner.Entity.Customer;
import com.example.pruefungsrechner.Form.LoginResponse;
import com.example.pruefungsrechner.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return "Gast";
    }

    public LoginResponse doesAliasExistsAndComparesPassword(String alias, String password) {
        Optional<Customer> customerOptional = customerRepository.findByAlias(alias);

        if (customerOptional.isEmpty()) {
            return LoginResponse.builder()
                    .message("Dieses Alias %s existiert nicht".formatted(alias))
                    .isCorrect(false)
                    .build();
        }

        Customer customer = customerOptional.get();
        boolean passwordCorrect = passwordEncoder.matches(password, customer.getPassword());

        return LoginResponse.builder()
                .message(passwordCorrect ? "Alles hat gepasst" : "Das Passwort stimmt nicht überein!!!")
                .isCorrect(passwordCorrect)
                .build();
    }

    @Transactional
    public boolean changePassword(String alias, String oldPassword, String newPassword) {
        Optional<Customer> customerOptional = customerRepository.findByAlias(alias);

        if (customerOptional.isEmpty()) {
            return false; // Kunde nicht gefunden
        }

        Customer customer = customerOptional.get();

        // Überprüfe, ob das alte Passwort korrekt ist
        if (!passwordEncoder.matches(oldPassword, customer.getPassword())) {
            return false; // Altes Passwort ist falsch
        }

        // Neues Passwort hashen und speichern
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
        return true; // Passwort wurde erfolgreich geändert
    }
}
