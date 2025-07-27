package com.bluebus.bookingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @Column(name = "passengerid", nullable = false, length = Integer.MAX_VALUE)
    private String passengerid;

    @Column(name = "bookingnumber", length = Integer.MAX_VALUE)
    private String bookingnumber;

}