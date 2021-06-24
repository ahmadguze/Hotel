package com.hotel.moels;
import com.hotel.helper.LocationException;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    private Double longitude;
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) throws LocationException {
        if(longitude>180|| longitude<-180)
            throw  new LocationException();
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitudes) throws LocationException {

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
