package com.example.pruefungsrechner.Model;

import lombok.Builder;

@Builder
public record CustomerModel(String alias, String email, String password) {
}
