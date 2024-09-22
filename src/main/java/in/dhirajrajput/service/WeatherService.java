package in.dhirajrajput.service;

import in.dhirajrajput.response_request.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

//    private static final String API_KEY = "5d44c782eaa8ce64b30d355dd2a9cfb1";

    @Value("${weather.api.key}")
    private String API_KEY;

    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeatherResponse(String city) {
        String finalApi = API.replace("API_KEY", API_KEY).replace("CITY", city);
        ResponseEntity<WeatherResponse> exchangeReponse = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        return exchangeReponse.getBody();
    }

}
