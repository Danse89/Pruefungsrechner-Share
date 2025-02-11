package com.example.pruefungsrechner.opentrivia;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenTriviaApiClient {
    // Basis-URL der OpenTriviaApi, ggf. Parameter anpassen
    private static final String API_URL = "https://opentdb.com/api.php?amount=10";

    private final HttpClient httpClient;

    public OpenTriviaApiClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String fetchTriviaData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
