package com.bluebus.bookingservice.controller;

import com.bluebus.bookingservice.entity.Booking;
import com.bluebus.bookingservice.services.BookingService;
import com.bluebus.bookingservice.services.BusInventoryService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class BookingServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceController.class);

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BusInventoryService busInventoryService;

    @PostMapping("addBooking")
    public ResponseEntity<String> addBooking(@RequestBody @NonNull Booking booking){
        LOGGER.info("entered into booking service");
        String result = busInventoryService.checkSeatAvailability(booking.getBusnumber());
        if(result ==null){
            return  ResponseEntity.ok("Wrong bus number");
        }else {
            if (Integer.parseInt(result) >= Integer.parseInt(booking.getNumberofseats())) {
                bookingService.createBooking(booking);
                return ResponseEntity.ok("Ticket Booked");
            } else {
                return ResponseEntity.ok("No seats available");
            }
        }
    }

    @GetMapping("fetchBooking/{bookingNumber}")
    public ResponseEntity<Optional<Booking>> fetchBooking(@PathVariable Integer bookingNumber){
        LOGGER.info("Fetching booking for booking number: {}", bookingNumber);
        if (bookingNumber == null || bookingNumber.equals(0)) {
            return ResponseEntity.badRequest().body(Optional.empty());
        }
        return ResponseEntity.ok(bookingService.fetchBooking(bookingNumber));
    }

    @PutMapping("editBooking")
    public ResponseEntity<String> editBooking(@RequestBody @NonNull Booking booking){
        LOGGER.info("Editing booking for booking number: {}", booking.getBookingnumber());
        bookingService.updateBooking(booking);
        LOGGER.info("Booking edited for booking number: {}", booking.getBookingnumber());
        return ResponseEntity.ok("booking edited");
    }

    @DeleteMapping("deleteBooking/{bookingNumber}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer bookingNumber){
        LOGGER.info("Deleting booking for booking number: {}", bookingNumber);
        bookingService.deleteBooking(bookingNumber);
        LOGGER.info("Booking deleted for booking number: {}", bookingNumber);
        return ResponseEntity.ok("booking deleted");
    }
}
