package com.hotel.moels;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hotel.helper.Haversine;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Entity
public class Hotel implements Comparable<Hotel> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Embedded
    private Location location;
    @ColumnDefault("0")
    private double rate;

    @ManyToOne
    private City city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @JsonBackReference
    public City getCity() {
        return city;
    }

    public void setCity(City cities) {
        this.city = cities;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", rate=" + rate +
                ", city=" + city +
                '}';
    }

    @Override
    public int compareTo(Hotel o) {
        Location location1 = getLocation();
        Location location2 = o.getLocation();
        Location cityLocation = this.city.getLocation();
        double differanceBetweenCityAndO1 = Haversine.haversine(location1.getLatitude(), location1.getLongitude()
                , cityLocation.getLatitude(), cityLocation.getLongitude());
        double differanceBetweenCityAndO2 =  Haversine.haversine(location2.getLatitude(), location2.getLongitude()
                , cityLocation.getLatitude(), cityLocation.getLongitude());
        return Double.compare(differanceBetweenCityAndO1, differanceBetweenCityAndO2);
    }
}
