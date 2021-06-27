package com.hotel.service.impl;

import com.hotel.helper.ElementNotFound;
import com.hotel.moels.Hotel;
import com.hotel.moels.Hotel;
import com.hotel.moels.Location;
import com.hotel.repositories.HotelRepository;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getOne(Long id) {
        return hotelRepository.findById(id).orElseThrow(ElementNotFound::new);
    }

    @Override
    public void delete(Long id) {
         hotelRepository.delete(getOne(id));
    }

    @Override
    public List<Hotel> getAll() {
        return (List<Hotel>) hotelRepository.findAll();
    }

    @Override
    public Hotel update(Hotel hotel, Long id) {
        Hotel existingHotel= getOne(id);
        existingHotel.setName(hotel.getName());
        existingHotel.setLocation(hotel.getLocation());
        return hotelRepository.save(existingHotel);

    }

    @Override
    public Hotel partiallyUpdate(Hotel hotel, Long id) {
        Hotel existingHotel= getOne(id);
        Location existingLocation = existingHotel.getLocation();
        Location location = hotel.getLocation();
        if(hotel.getName() != null)
            if(location  != null){
                if(location.getLatitude() != null)
                    existingLocation.setLatitude(location.getLatitude());
                if(location.getLongitude() != null)
                    existingLocation.setLongitude(location.getLongitude());
            }
       return hotelRepository.save(existingHotel);

    }

}
