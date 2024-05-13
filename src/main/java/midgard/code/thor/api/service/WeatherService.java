package midgard.code.thor.api.service;

import midgard.code.thor.api.model.dto.WeatherForecastDto;

public interface WeatherService {
    WeatherForecastDto getWeatherForecast(String location);
}
