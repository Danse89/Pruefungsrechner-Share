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

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return "Gast";
    }

    public LoginResponse doesAliasExistsAndComparesPassword(String alias, String password) {
        Optional<Customer> customerOptional = customerRepository.findByAlias(alias);

        if (customerOptional.isEmpty()) {
            return LoginResponse.builder().message("Dieses Alias %s existiert nicht".formatted(alias)).isCorrect(false).build();
        }

        Customer customer = customerOptional.get();
        boolean passwordCorrect = passwordEncoder.matches(password, customer.getPassword());

        return LoginResponse.builder()
                .message(passwordCorrect ? "Alles hat gepasst" : "Das Passwort stimmt nicht überein!!!")
                .isCorrect(passwordCorrect)
                .build();

    }

}
