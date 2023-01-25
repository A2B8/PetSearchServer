package com.base.PetSearchServer.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Ad {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser creator;

    @Id
    @SequenceGenerator(
            name ="ad_sequence",
            sequenceName = "ad_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ad_sequence"
    )
    private long id;

    private Boolean isLost;
    private String animal;
    private String gender;
    private LocalDate date;
    private String address;
    private float geoLat;
    private float geoLon;
    private String description;
    private String filename;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getLost() {
        return isLost;
    }

    public void setLost(Boolean isLost) {
        this.isLost = isLost;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(float geoLat) {
        this.geoLat = geoLat;
    }

    public float getGeoLon() {
        return geoLon;
    }

    public void setGeoLon(float geoLon) {
        this.geoLon = geoLon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Ad(Boolean isLost,
              String animal,
              String gender,
              LocalDate date,
              String address,
              float geoLat,
              float geoLon,
              String description
             // AppUser appUser
              ) {
        this.isLost = isLost;
        this.animal = animal;
        this.gender = gender;
        this.date = date;
        this.address = address;
        this.geoLat = geoLat;
        this.geoLon = geoLon;
        this.description = description;
        //this.creator = appUser;
    }
}
