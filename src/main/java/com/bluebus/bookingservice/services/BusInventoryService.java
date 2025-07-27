package com.bluebus.bookingservice.services;

import com.bluebus.bookingservice.entity.Booking;
import com.bluebus.bookingservice.entity.Businventory;
import com.bluebus.bookingservice.repo.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Service
public class BusInventoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusInventoryService.class);

    @Autowired
    private WebClient webClient;

    public String checkSeatAvailability(String busnumber) {
        Businventory businventoryResponseEntity = webClient
                .get()
                .uri("http://localhost:8072/inventory-service/api/v1/fetchInventory/"+busnumber)
                .retrieve()
                .bodyToMono(Businventory.class)
                .block();
        if(businventoryResponseEntity!=null){
            return Objects.requireNonNull(businventoryResponseEntity).getAvailableseats();

        }
        return null;
    }
}
