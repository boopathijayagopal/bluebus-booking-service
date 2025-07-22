package com.bluebus.bookingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @Column(name = "passengerid", nullable = false, length = Integer.MAX_VALUE)
    private String passengerid;

    @Column(name = "bookingnumber", length = Integer.MAX_VALUE)
    private String bookingnumber;

    public String getPassengerid() {
        return passengerid;
    }

    public void setPassengerid(String passengerid) {
        this.passengerid = passengerid;
    }

    public String getBookingnumber() {
        return bookingnumber;
    }

    public void setBookingnumber(String bookingnumber) {
        this.bookingnumber = bookingnumber;
    }

}