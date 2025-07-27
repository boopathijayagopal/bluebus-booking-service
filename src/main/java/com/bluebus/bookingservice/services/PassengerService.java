package com.bluebus.bookingservice.services;

import com.bluebus.bookingservice.entity.Passenger;
import com.bluebus.bookingservice.repo.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PassengerService.class);

    @Autowired
    private PassengerRepository passengerRepository;

    public void updatePassenger(Passenger passenger) {
        LOGGER.info("Updating passenger with passenger ID: {}", passenger.getPassengerid());
        passengerRepository.save(passenger);
        LOGGER.info("Passenger updated with passenger ID: {}", passenger.getPassengerid());
    }
}
