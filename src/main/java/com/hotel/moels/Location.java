package com.hotel.moels;

import javax.persistence.Embeddable;
import java.util.Comparator;
@Embeddable
public class Location{
    private double longitude;
    private double latitude;




    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitudes() {
        return latitude;
    }

    public void setLatitudes(double latitudes) {
        this.latitude = latitudes;
    }

    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
