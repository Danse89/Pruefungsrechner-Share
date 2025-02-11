package com.example.pruefungsrechner.opentrivia;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class OpenTriviaService {

    private final OpenTriviaApiClient apiClient;
    private final ObjectMapper objectMapper;

    public OpenTriviaService() {
        this.apiClient = new OpenTriviaApiClient();
        this.objectMapper = new ObjectMapper();
    }

    public TriviaResponse getTriviaQuestions() throws IOException, InterruptedException {
        String jsonResponse = apiClient.fetchTriviaData();
        // Mapping der JSON-Antwort in das TriviaResponse-Objekt
        return objectMapper.readValue(jsonResponse, TriviaResponse.class);
    }
}
