package com.location.location.thirdPartyClients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Component
public class WeatherClient {

    private String baseUrl = "https://gg-backend-assignment.azurewebsites.net/api/Weather?code=KfQnTWHJbg1giyB_Q9Ih3Xu3L9QOBDTuU5zwqVikZepCAzFut3rqsg==&";
    public WeatherClient(String city, Date date) {
        this.baseUrl = this.baseUrl + "city=" + city + "&date=" + date;

    }
    public String getWeather(String apiUrl) {
        String weatherResponse = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Mapping> response = restTemplate
                .getForEntity(apiUrl, Mapping.class);
        if(response == null){
            throw  new RuntimeException("Error fetching weather data from the API");
        }
      else  {
          Mapping responseEntity = response.getBody();
          weatherResponse = responseEntity.getWeather();
//          System.out.println("response = " + responseEntity.getWeather());

        }
        return weatherResponse;
    }
}

//if (response != null && response.getStatusCode() == HttpStatus.OK)