package midgard.code.thor.api.service;

import midgard.code.thor.api.exception.InternalServerException;
import midgard.code.thor.api.exception.InvalidRequestException;
import midgard.code.thor.api.model.dto.RealTimeWeatherDto;
import midgard.code.thor.api.model.dto.WeatherForecastDto;
import midgard.code.thor.api.utils.HttpClientManager;
import midgard.code.thor.api.utils.HttpClientManagerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${api.tomorrow-io.endpoints.weather-forecast}")
    private String weatherForecastEndpoint;

    @Value("${api.tomorrow-io.endpoints.real-time-weather}")
    private String realTimeWeatherEndpoint;

    @Value("${api.tomorrow-io.api-key}")
    private String apiKey;

    @Override
    @Cacheable("forecastCache")
    public WeatherForecastDto getWeatherForecast(String location) throws InternalServerException, InvalidRequestException {
        HttpClientManager httpClientManager = new HttpClientManagerImpl();

        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("location", location);
        queryParams.put("apikey", apiKey);

        return httpClientManager.get(weatherForecastEndpoint, queryParams, WeatherForecastDto.class);
    }

    @Override
    public RealTimeWeatherDto getRealTimeWeather(String location) throws InternalServerException, InvalidRequestException {
        HttpClientManager httpClientManager = new HttpClientManagerImpl();

        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("location", location);
        queryParams.put("apikey", apiKey);

        return httpClientManager.get(realTimeWeatherEndpoint, queryParams, RealTimeWeatherDto.class);
    }
}
