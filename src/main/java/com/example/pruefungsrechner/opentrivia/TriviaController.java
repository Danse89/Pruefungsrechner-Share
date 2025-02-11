package com.example.pruefungsrechner.opentrivia;

import com.example.pruefungsrechner.opentrivia.TriviaResponse;
import com.example.pruefungsrechner.opentrivia.OpenTriviaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trivia")
public class TriviaController {

    private final OpenTriviaService openTriviaService;

    @Autowired
    public TriviaController(OpenTriviaService openTriviaService) {
        this.openTriviaService = openTriviaService;
    }

    @GetMapping
    public ResponseEntity<TriviaResponse> getTrivia() {
        try {
            TriviaResponse response = openTriviaService.getTriviaQuestions();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Logge den Fehler, wenn nötig
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
