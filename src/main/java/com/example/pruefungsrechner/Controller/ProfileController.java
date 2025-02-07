package com.example.pruefungsrechner.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.example.pruefungsrechner.Entity.Customer;
import com.example.pruefungsrechner.Repository.CustomerRepository;
import com.example.pruefungsrechner.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @GetMapping("/Profile")
    public String profile(Model model) {
        Optional<Customer> customer = customerRepository.findByAlias(webSecurityConfig.getCurrentUser());
        String alias = "Benutzer123"; // Beispiel: aktuell eingeloggter Alias
        String hashedPassword = "e99a18c428cb38d5f260853678922e03"; // Beispielhaft gehashter Passwort-String

        // Aktuelles Datum und Uhrzeit im gewünschten Format (z.B. "31.01.2025 15:45:30")
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String currentDateTime = now.format(formatter);

        // Die Daten als Model-Attribute zur Verfügung stellen
        model.addAttribute("alias", customer.get().getAlias());
        model.addAttribute("hashedPassword", customer.get().getPassword());
        model.addAttribute("currentDateTime", currentDateTime);

        // Die View "profile.html" (befindet sich üblicherweise unter src/main/resources/templates)
        return "Profile";
    }
}
