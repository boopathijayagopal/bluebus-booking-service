package com.bluebus.bookingservice.controller;

import com.bluebus.bookingservice.repo.BookingRepository;
import com.bluebus.bookingservice.services.BookingService;
import com.bluebus.bookingservice.repo.PassengerRepository;
import com.bluebus.bookingservice.entity.Booking;
import com.bluebus.bookingservice.entity.Passenger;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("api/v1")
public class BookingServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceController.class);

    BookingRepository bookingRepository;
    PassengerRepository passengerRepository;
    BookingService bookingService;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    Environment environment;

    BookingServiceController(BookingRepository bookingRepository, PassengerRepository passengerRepository,
                             BookingService bookingService)
    {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.bookingService = bookingService;
    }
    @PostMapping("addBooking")
    public ResponseEntity<String> addBooking(@RequestBody Booking booking){
        LOGGER.info("entered into booking service");
        String result = bookingService.checkSeatAvailability(booking.getBusnumber());
        if(result ==null){
            return  ResponseEntity.ok("Wrong bus number");
        }else {
            if (Integer.parseInt(result) >= Integer.parseInt(booking.getNumberofseats())) {

                bookingRepository.save(booking);

                jmsTemplate.convertAndSend("busservice", booking.toString());
                return ResponseEntity.ok("Ticket Booked");
            } else {
                return ResponseEntity.ok("No seats available");
            }
        }

    }

    @GetMapping("fetchBooking/{bookingNumber}")
    public ResponseEntity<Optional<Booking>> fetchBooking(@PathVariable Integer bookingNumber){
        return ResponseEntity.ok(bookingRepository.findById(bookingNumber));
    }

    @JmsListener(destination = "inventory-service-queue")
    public void receiveMessage(@Payload String message){
        LOGGER.info("Received message: {}", message);
        Booking booking = new Gson().fromJson(message, Booking.class);
        LOGGER.info("Message Consumed   {}",booking.getBookingnumber());
        booking.setStatus("Confirmed");
        editBooking(booking);
        Random rand = new Random();
        for (int i = 0; i < Integer.parseInt(booking.getNumberofseats()); i++) {
            Passenger passenger = new Passenger();
            passenger.setPassengerid(String.valueOf(rand.nextInt(1000)));
            passenger.setBookingnumber(String.valueOf(booking.getBookingnumber()));
            passengerRepository.save(passenger);
        }
    }

    @PutMapping("editBooking")
    public ResponseEntity<String> editBooking(@RequestBody Booking booking){
        bookingRepository.save(booking);
        return ResponseEntity.ok("booking edited");
    }

    @DeleteMapping("deleteBooking/{bookingNumber}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer bookingNumber){
        bookingRepository.deleteById(bookingNumber);
        return ResponseEntity.ok("booking deleted");
    }

}
