package midgard.code.thor.api.service;

import midgard.code.thor.api.model.dto.WeatherForecastDto;
import midgard.code.thor.api.utils.HttpClientManager;
import midgard.code.thor.api.utils.HttpClientManagerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${api.tomorrow-io.endpoints.weather-forecast}")
    private String weatherForecastEndpoint;

    @Value("${api.tomorrow-io.api-key}")
    private String apiKey;

    @Override
    public WeatherForecastDto getWeatherForecast(String location) {
        HttpClientManager httpClientManager = new HttpClientManagerImpl();

        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("location", location);
        queryParams.put("apikey", apiKey);

        return httpClientManager.get(weatherForecastEndpoint, queryParams, WeatherForecastDto.class);
    }
}
