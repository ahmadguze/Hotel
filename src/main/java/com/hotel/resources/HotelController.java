package com.hotel.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.helper.ElementNotFound;
import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.moels.Hotel;
import com.hotel.moels.Location;
import com.hotel.repositories.HotelRepository;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @DeleteMapping("hotel/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        hotelRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("hotel")
    public List<Hotel> showAll(){
        return (List<Hotel>) hotelRepository.findAll();
    }

    @PostMapping(value = "hotel")
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel create(@RequestBody Hotel hotel){
        return hotelRepository.save(hotel);
    }

    @GetMapping("hotel/{id}")
    public Hotel getOne(@PathVariable Long id){
         return hotelRepository.findById(id).orElseThrow(()->new ElementNotFound());
    }

    @PutMapping(value = "hotel/{id}")
    public ResponseEntity<Hotel> update(@PathVariable Long id,@RequestBody Hotel hotel){
        Hotel existingHotel= getOne(id);
        existingHotel.setName(hotel.getName());
        existingHotel.setLocation(hotel.getLocation());
        Hotel savedHotel = hotelRepository.save(existingHotel);
        return new ResponseEntity(savedHotel, HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "city/{id}")
    public ResponseEntity<City> PartiallyUpdate(@PathVariable Long id, @RequestBody Hotel hotel){

        Hotel existingHotel= getOne(id);
        Location existingLocation = existingHotel.getLocation();
        Location location = hotel.getLocation();
        if(hotel.getName() != null)
            existingHotel.setName(hotel.getName());
        if(location  != null){
            if(location.getLatitude() != null)
                existingLocation.setLatitude(location.getLatitude());
            if(location.getLongitude() != null)
                existingLocation.setLongitude(location.getLongitude());
        }
        Hotel savedHotel = hotelRepository.save(existingHotel);
        return new ResponseEntity(savedHotel, HttpStatus.OK);
    }





}
