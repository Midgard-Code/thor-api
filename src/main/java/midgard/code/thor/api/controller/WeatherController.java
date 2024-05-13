package midgard.code.thor.api.controller;

import midgard.code.thor.api.model.dto.WeatherForecastDto;
import midgard.code.thor.api.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather-forecast")
    public ResponseEntity<WeatherForecastDto> getWeatherForecast(@RequestParam String location) {
        return ResponseEntity.ok(weatherService.getWeatherForecast(location));
    }
}
