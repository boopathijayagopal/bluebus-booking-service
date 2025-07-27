package com.bluebus.bookingservice.services;

import com.bluebus.bookingservice.entity.Booking;
import com.bluebus.bookingservice.exception.ResourceNotFoundException;
import com.bluebus.bookingservice.repo.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class BookingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void createBooking(Booking booking) {
        LOGGER.info("Creating booking for bus number: {}", booking.getBusnumber());
        booking.setCreationdate(getDateTime());
        booking.setLastupdateddate(getDateTime());
        booking.setStatus("In Progress");
        bookingRepository.save(booking);
        jmsTemplate.convertAndSend("busservice", booking.toString());
        LOGGER.info("Booking created with booking number: {}", booking.getBookingnumber());
    }

    public Optional<Booking> fetchBooking(Integer bookingNumber) {
        LOGGER.info("Fetching booking with booking number: {}", bookingNumber);
        return Optional.ofNullable(bookingRepository.findById(bookingNumber).orElseThrow(() -> {
            LOGGER.error("Booking with booking number: {} not found", bookingNumber);
            return new ResourceNotFoundException("Booking not found");
        }));
    }

    public void updateBooking(Booking booking) {
        LOGGER.info("Updating booking with booking number: {}", booking.getBookingnumber());
        Booking existingBooking = fetchBooking(booking.getBookingnumber()).orElseThrow();
        if (!existingBooking.getStatus().equals("In Progress")) {
            LOGGER.error("Cannot update booking with booking number: {} as it is not in progress", booking.getBookingnumber());
            throw new IllegalStateException("Booking is not in progress and cannot be updated");
        }
        existingBooking.setBookingdate(booking.getBookingdate());
        existingBooking.setBusnumber(booking.getBusnumber());
        existingBooking.setSource(booking.getSource());
        existingBooking.setDestination(booking.getDestination());
        existingBooking.setNumberofseats(booking.getNumberofseats());
        existingBooking.setBookingnumber(booking.getBookingnumber());
        existingBooking.setLastupdateddate(getDateTime());
        bookingRepository.save(booking);
        LOGGER.info("Booking updated with booking number: {}", booking.getBookingnumber());
    }

    public void deleteBooking(Integer bookingNumber) {
        LOGGER.info("Deleting booking with booking number: {}", bookingNumber);
        bookingRepository.deleteById(bookingNumber);
        LOGGER.info("Booking deleted with booking number: {}", bookingNumber);
    }

    private String getDateTime() {
        LOGGER.info("Generating current date and time in dd-MM-yyyy HH:mm:ss format");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(dateTimeFormatter);
    }
}
