package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Entity.Customer;
import com.example.pruefungsrechner.Form.LoginResponse;
import com.example.pruefungsrechner.Repository.CustomerRepository;
import com.example.pruefungsrechner.Service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String home(HttpSession session) {
        // Falls der Benutzer eingeloggt ist, zur Startseite weiterleiten
        if (session.getAttribute("user") != null) {
            return "redirect:/startseite";
        }
        return "redirect:/login"; // Ansonsten zur Login-Seite
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String alias, @RequestParam String password, HttpSession session, Model model) {
        LoginResponse loginResponse = customerService.doesAliasExistsAndComparesPassword(alias, password);
        if (loginResponse.isCorrect()) {
            session.setAttribute("user", alias);
            return "redirect:/startseite"; // Nach dem Login zur Startseite weiterleiten
        } else {
            model.addAttribute("error", loginResponse.message()); // Fehler anzeigen
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String alias, @RequestParam String email, @RequestParam String password, Model model) {
        try {
            customerRepository.save(Customer.builder().alias(alias).email(email).password(password).build());
            return "redirect:/login"; // Erfolgreich registriert → Weiterleitung zur Login-Seite
        } catch (Exception e) {
            model.addAttribute("error", "Invalid alias or password");
            return "login";
        }
    }
}
