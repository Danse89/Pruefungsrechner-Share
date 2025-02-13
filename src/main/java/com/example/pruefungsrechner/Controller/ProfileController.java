package com.example.pruefungsrechner.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.example.pruefungsrechner.Entity.Customer;
import com.example.pruefungsrechner.Repository.CustomerRepository;
import com.example.pruefungsrechner.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfileController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/profile")
    public String profile(Model model) {
        String currentUser = customerService.getCurrentUser();
        Optional<Customer> customer = customerRepository.findByAlias(currentUser);

        if (customer.isPresent()) {
            model.addAttribute("alias", customer.get().getAlias());
            model.addAttribute("hashedPassword", customer.get().getPassword());
        } else {
            model.addAttribute("alias", "Unbekannt");
            model.addAttribute("hashedPassword", "Nicht verfügbar");
        }

        // Aktuelles Datum und Uhrzeit
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String currentDateTime = now.format(formatter);
        model.addAttribute("currentDateTime", currentDateTime);

        return "profile";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 RedirectAttributes redirectAttributes) {
        String currentUser = customerService.getCurrentUser();

        if (customerService.changePassword(currentUser, oldPassword, newPassword)) {
            redirectAttributes.addFlashAttribute("message", "Passwort erfolgreich geändert.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Altes Passwort ist falsch.");
        }

        return "redirect:/profile";
    }
}
