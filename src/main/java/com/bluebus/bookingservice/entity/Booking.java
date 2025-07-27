package com.bluebus.bookingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "booking")
public class Booking implements Serializable {
    @Id
    @Column(name = "bookingnumber", nullable = false, length = Integer.MAX_VALUE)
    private Integer bookingnumber;

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

    @Column(name = "creationdate", length = Integer.MAX_VALUE)
    private String creationdate;

    @Column(name = "lastupdateddate", length = Integer.MAX_VALUE)
    private String lastupdateddate;
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
                ", creationdate='" + creationdate + '\'' +
                ", lastupdateddate='" + lastupdateddate + '\'' +
                '}';
    }
}