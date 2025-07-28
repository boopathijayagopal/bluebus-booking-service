package com.bluebus.bookingservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AuthValidationService {

    @Autowired
    private WebClient webClient;

    @Autowired
    EurekaDiscoveryClient discoveryClient;

    public boolean validateToken(String token) {
        List<ServiceInstance> instances = discoveryClient.getInstances("auth-service");
        //No load balancing algorithm is used here, so we are just taking the first instance
        // you can use load balancing algorithm like round robin or random if you want
        String hostname = instances.get(0).getHost();
        String port = String.valueOf(instances.get(0).getPort());

        String response = webClient
                .get()
                .uri("http://"+hostname+":"+port+"/api/v1/auth/validateToken")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response != null && response.equals("Token is valid");
    }
}
