package com.example.pruefungsrechner.Service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationRequest {
    private String mail;
    private String password;
}