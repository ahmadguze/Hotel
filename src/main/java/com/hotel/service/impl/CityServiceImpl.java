package com.hotel.service.impl;

import com.hotel.helper.ElementNotFound;
import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.moels.Location;
import com.hotel.repositories.CityRepository;
import com.hotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;
    @Override
    public City create(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getOne(Long id) {
        return cityRepository.findById(id).orElseThrow(ElementNotFound::new);
    }

    @Override
    public void delete(Long id) {
         cityRepository.delete(getOne(id));
    }

    @Override
    public List<City> getAll() {
        return (List<City>) cityRepository.findAll();
    }

    @Override
    public City update(City city, Long id) {
        City existingCity= getOne(id);
        existingCity.setName(city.getName());
        existingCity.setLocation(city.getLocation());
        return cityRepository.save(existingCity);

    }

    @Override
    public City partiallyUpdate(City city, Long id) {
        City existingCity= getOne(id);
        Location existingLocation = existingCity.getLocation();
        Location location = city.getLocation();
        if(city.getName() != null)
            if(location  != null){
                if(location.getLatitude() != null)
                    existingLocation.setLatitude(location.getLatitude());
                if(location.getLongitude() != null)
                    existingLocation.setLongitude(location.getLongitude());
            }
       return cityRepository.save(existingCity);

    }

    @Override
    public List<Hotel> nearestThreeHotels(Long id) {
        City city = getOne(id);
        return city.ThreeNearest();
    }
}
