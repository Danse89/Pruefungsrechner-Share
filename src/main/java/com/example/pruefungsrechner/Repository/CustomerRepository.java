package com.example.pruefungsrechner.Repository;

import com.example.pruefungsrechner.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Methode zum Finden eines Kunden anhand der E-Mail
    Optional<Customer> findByEmail(String email);

    // Methode zum Überprüfen, ob eine E-Mail bereits existiert
    boolean existsByEmail(String email);
}
