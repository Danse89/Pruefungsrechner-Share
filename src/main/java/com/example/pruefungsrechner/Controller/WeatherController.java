package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Map<String, Object> getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.getWeatherData(latitude, longitude);
    }
}
