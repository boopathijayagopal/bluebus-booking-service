package com.bluebus.bookingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "businventory")
public class Businventory {
    @Id
    @Column(name = "busnumber", nullable = false, length = Integer.MAX_VALUE)
    private String busnumber;

    @Column(name = "availableseats", length = Integer.MAX_VALUE)
    private String availableseats;

    @Column(name = "lastupdateddate", length = Integer.MAX_VALUE)
    private String lastupdateddate;

    public String getBusnumber() {
        return busnumber;
    }

    public void setBusnumber(String busnumber) {
        this.busnumber = busnumber;
    }

    public String getAvailableseats() {
        return availableseats;
    }

    public void setAvailableseats(String availableseats) {
        this.availableseats = availableseats;
    }

    public String getLastupdateddate() {
        return lastupdateddate;
    }

    public void setLastupdateddate(String lastupdateddate) {
        this.lastupdateddate = lastupdateddate;
    }

}