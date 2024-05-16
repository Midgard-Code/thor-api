package midgard.code.thor.api.service;

import midgard.code.thor.api.exception.InternalServerException;
import midgard.code.thor.api.exception.InvalidRequestException;
import midgard.code.thor.api.model.dto.RealTimeWeatherDto;
import midgard.code.thor.api.model.dto.WeatherForecastDto;

public interface WeatherService {
    WeatherForecastDto getWeatherForecast(String location) throws InternalServerException, InvalidRequestException;

    RealTimeWeatherDto getRealTimeWeather(String location) throws InternalServerException, InvalidRequestException;
}
