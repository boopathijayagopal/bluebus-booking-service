package com.bluebus.bookingservice.services;

import com.bluebus.bookingservice.entity.Businventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class BookingService {

    @Autowired
    private WebClient webClient;


    public String checkSeatAvailability(String busnumber) {
        Businventory businventoryResponseEntity = webClient.get().uri("http://localhost:8072/inventory-service/api/v1/fetchInventory/"+busnumber).retrieve()
                .bodyToMono(Businventory.class).block();
        if(businventoryResponseEntity!=null){
            return Objects.requireNonNull(businventoryResponseEntity).getAvailableseats();

        }
        return null;
    }
}
