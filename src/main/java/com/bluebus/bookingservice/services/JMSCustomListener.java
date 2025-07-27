package com.bluebus.bookingservice.services;

import com.bluebus.bookingservice.entity.Booking;
import com.bluebus.bookingservice.entity.Passenger;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class JMSCustomListener {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PassengerService passengerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSCustomListener.class);
    @JmsListener(destination = "inventory-service-queue")
    public void receiveMessage(@Payload String message){
        LOGGER.info("Received message: {}", message);
        Booking booking = new Gson().fromJson(message, Booking.class);
        LOGGER.info("Message Consumed   {}",booking.getBookingnumber());
        booking.setStatus("Confirmed");
        bookingService.updateBooking(booking);
        Random rand = new Random();
        for (int i = 0; i < Integer.parseInt(booking.getNumberofseats()); i++) {
            Passenger passenger = new Passenger();
            passenger.setPassengerid(String.valueOf(rand.nextInt(1000)));
            passenger.setBookingnumber(String.valueOf(booking.getBookingnumber()));
            passengerService.updatePassenger(passenger);
        }
    }
}
