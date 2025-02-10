package com.example.pruefungsrechner.Repository;

import com.example.pruefungsrechner.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByAlias(String alias);
}
