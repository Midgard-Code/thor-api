package midgard.code.thor.api.controller;

import midgard.code.thor.api.exception.InternalServerException;
import midgard.code.thor.api.exception.InvalidRequestException;
import midgard.code.thor.api.model.dto.RealTimeWeatherDto;
import midgard.code.thor.api.model.dto.WeatherForecastDto;
import midgard.code.thor.api.model.result.Response;
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
    public ResponseEntity<Response<WeatherForecastDto>> getWeatherForecast(@RequestParam String location) throws InternalServerException, InvalidRequestException {
        return ResponseEntity.ok(new Response<>(weatherService.getWeatherForecast(location)));
    }

    @GetMapping("/api/real-time")
    public ResponseEntity<Response<RealTimeWeatherDto>> getRealTimeWeather(@RequestParam String location) throws InternalServerException, InvalidRequestException {
        return ResponseEntity.ok(new Response<>(weatherService.getRealTimeWeather(location)));
    }
}
