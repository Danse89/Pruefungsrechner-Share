package com.example.pruefungsrechner.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${openweathermap.api.url}")
    private String apiUrl;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getWeatherData(double latitude, double longitude) {
        // Beispiel für den Current Weather Data Endpoint
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric&lang=de",
                apiUrl, latitude, longitude, apiKey);
        return restTemplate.getForObject(url, Map.class);
    }
}
