package com.example.pruefungsrechner.Repository;

import com.example.pruefungsrechner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}