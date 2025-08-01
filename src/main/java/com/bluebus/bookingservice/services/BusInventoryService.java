package com.bluebus.bookingservice.services;

import com.bluebus.bookingservice.entity.Businventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class BusInventoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusInventoryService.class);

    @Autowired
    private WebClient webClient;

    @Autowired
    EurekaDiscoveryClient discoveryClient;

    public String checkSeatAvailability(String busnumber, String token) {
        LOGGER.info("Checking seat availability for bus number: {}", busnumber);
        List<ServiceInstance> instances = discoveryClient.getInstances("inventory-service");
        //No load balancing algorithm is used here, so we are just taking the first instance
        // you can use load balancing algorithm like round robin or random if you want
        String hostname = instances.get(0).getHost();
        String port = String.valueOf(instances.get(0).getPort());
        Businventory businventoryResponseEntity = webClient
                .get()
                .uri("http://"+hostname+":"+port+"/api/v1/fetchInventory/"+busnumber)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Businventory.class)
                .block();
        if(businventoryResponseEntity!=null){
            return Objects.requireNonNull(businventoryResponseEntity).getAvailableseats();

        }
        return null;
    }
}
