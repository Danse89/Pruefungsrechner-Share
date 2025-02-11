package com.example.pruefungsrechner.opentrivia;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TriviaResponse {
    @JsonProperty("response_code")
    private int responseCode;

    @JsonProperty("results")
    private List<TriviaQuestion> results;

    // Getter und Setter
    public int getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public List<TriviaQuestion> getResults() {
        return results;
    }
    public void setResults(List<TriviaQuestion> results) {
        this.results = results;
    }
}

