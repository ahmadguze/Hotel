package com.hotel.helper;

import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.moels.Location;
import org.springframework.stereotype.Service;

import java.util.Comparator;

public class hotelsComparator implements Comparator<Hotel> {

    private Location cityLocation;
    private double haversine(double lat1, double lon1,
                             double lat2, double lon2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    @Override
    public int compare(Hotel o1, Hotel o2) {
        Location location1 = o1.getLocation();
        Location location2 = o2.getLocation();

        double differanceBetweenCityAndO1 = haversine(location1.getLatitudes(), location1.getLongitude()
                , cityLocation.getLatitudes(), cityLocation.getLongitude());
        double differanceBetweenCityAndO2 = haversine(location2.getLatitudes(), location2.getLongitude()
                , cityLocation.getLatitudes(), cityLocation.getLongitude());
        return Double.compare(differanceBetweenCityAndO1, differanceBetweenCityAndO2);

    }

    public hotelsComparator(Location cityLocation) {
        this.cityLocation = cityLocation;
    }
}


