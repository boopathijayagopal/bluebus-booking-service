package com.bluebus.bookingservice.repo;

import com.bluebus.bookingservice.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}