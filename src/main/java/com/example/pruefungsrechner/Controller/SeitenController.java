package com.example.pruefungsrechner.Controller;

import com.example.pruefungsrechner.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class SeitenController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/startseite")
    public String startseite(Model model) {
        double lat = 52.52;
        double lon = 13.4050;

        // Wetterdaten abrufen
        Map<String, Object> currentWeather = weatherService.getWeatherData(lat, lon);

        if (currentWeather != null && currentWeather.containsKey("current")) {
            Map<String, Object> currentWeatherData = (Map<String, Object>) currentWeather.get("current");

            Double temperature = (Double) currentWeatherData.get("temperature_2m");
            String description = "Unbekannt"; // Hier könntest du ggf. eine Umwandlung des Wettercodes hinzufügen

            model.addAttribute("temperature", temperature);
            model.addAttribute("weatherDescription", description);
        } else {
            model.addAttribute("temperature", "Keine Daten");
            model.addAttribute("weatherDescription", "Keine Wetterdaten verfügbar");
        }

        return "startseite";
    }
}
