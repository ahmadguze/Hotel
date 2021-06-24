package com.hotel.moels;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Location {
    @Size(min = -180, max = 180)
    private Double longitude;
    @Size(min = -90, max = 90)
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitudes){
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
