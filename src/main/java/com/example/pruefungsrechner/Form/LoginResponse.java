package com.example.pruefungsrechner.Form;

import lombok.Builder;

@Builder
public record LoginResponse(String message, boolean isCorrect) {
}
