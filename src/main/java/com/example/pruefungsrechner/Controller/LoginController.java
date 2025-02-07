package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Entity.Customer;
import com.example.pruefungsrechner.Repository.CustomerRepository;
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


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String alias, @RequestParam String password, HttpSession session, Model model) {
        if (customerRepository.findByAlias(alias).orElseThrow().getPassword().equals(password)) {

            session.setAttribute("user", alias);

            return "redirect:/startseite";
        } else {
            model.addAttribute("error", "Ungültige Anmeldung");
            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String alias, @RequestParam String password, Model model) {
        System.out.println("Test");
        try {
            customerRepository.save(Customer.builder().alias(alias).password(password).build());

            return "redirect:/login"; //pop up mit success
        } catch (Exception e) {
            model.addAttribute("error", "Invalid alias or password");
            return "redirect:/login";
        }
    }

}
