package com.hotel.moels;
import com.hotel.helper.LocationException;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) throws Exception {
        if(longitude>180|| longitude<-180)
            throw  new LocationException();
        this.longitude = longitude;
    }

    public double getLatitudes() {
        return latitude;
    }

    public void setLatitudes(double latitudes) throws Exception {

        if(longitude>90 || longitude<-90)
            throw new LocationException();
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
