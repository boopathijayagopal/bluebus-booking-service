package com.bluebus.bookingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {
    @Id
    @Column(name = "bookingnumber", nullable = false, length = Integer.MAX_VALUE)
    private String bookingnumber;

    @Column(name = "busnumber", length = Integer.MAX_VALUE)
    private String busnumber;

    @Column(name = "bookingdate", length = Integer.MAX_VALUE)
    private String bookingdate;

    @Column(name = "source", length = Integer.MAX_VALUE)
    private String source;

    @Column(name = "destination", length = Integer.MAX_VALUE)
    private String destination;

    @Column(name = "numberofseats", length = Integer.MAX_VALUE)
    private String numberofseats;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    public String getBookingnumber() {
        return bookingnumber;
    }

    public void setBookingnumber(String bookingnumber) {
        this.bookingnumber = bookingnumber;
    }

    public String getBusnumber() {
        return busnumber;
    }

    public void setBusnumber(String busnumber) {
        this.busnumber = busnumber;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNumberofseats() {
        return numberofseats;
    }

    public void setNumberofseats(String numberofseats) {
        this.numberofseats = numberofseats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "bookingnumber='" + bookingnumber + '\'' +
                ", busnumber='" + busnumber + '\'' +
                ", bookingdate='" + bookingdate + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", numberofseats='" + numberofseats + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}