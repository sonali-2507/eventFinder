package com.location.location.thirdPartyClients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
@NoArgsConstructor
@Component
public class DistanceClient {
    private String baseUrl = "https://gg-backend-assignment.azurewebsites.net/api/Distance?code=IAKvV2EvJa6Z6dEIUqqd7yGAu7IZ8gaH-a0QO6btjRc1AzFu8Y3IcQ==&latitude1=40.7128&longitude1=-74.0060";

    public DistanceClient(double latitude2, double longitude2) {
        this.baseUrl = this.baseUrl +  "&latitude2=" + latitude2 + "&longitude2=" + longitude2;
    }
    public Double getDistance(String apiUrl) {
        Double distanceResponse = 0.0;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DistanceMapping> response = restTemplate
                .getForEntity(apiUrl, DistanceMapping.class);
        if(response == null){
            throw  new RuntimeException("Error fetching distance data from the API");
        }
        else  {
            DistanceMapping responseEntity = response.getBody();
            distanceResponse = responseEntity.getDistance();
//            System.out.println("response = " + responseEntity.getDistance());

        }
        return distanceResponse;
    }
}

//if (response != null && response.getStatusCode() == HttpStatus.OK)