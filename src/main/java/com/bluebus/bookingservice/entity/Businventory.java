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
@Table(name = "businventory")
public class Businventory {
    @Id
    @Column(name = "busnumber", nullable = false, length = Integer.MAX_VALUE)
    private String busnumber;

    @Column(name = "availableseats", length = Integer.MAX_VALUE)
    private String availableseats;

    @Column(name = "lastupdateddate", length = Integer.MAX_VALUE)
    private String lastupdateddate;

}