package com.hotel.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.helper.LocationException;
import com.hotel.moels.Hotel;
import com.hotel.moels.Hotel;
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

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LocationException.class)
    public String handleLocationException(){
        return "longitude must be between -180 and 180 and latitude between -90 and 90";
    }

    @PostMapping(value = "hotel")
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel create(@RequestBody Hotel hotel){
        return hotelRepository.save(hotel);
    }

    @GetMapping("hotel/{id}")
    public ResponseEntity<Hotel> getOne(@PathVariable Long id){
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(!hotel.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Hotel>(hotel.get(), HttpStatus.OK);
    }

    @PutMapping(value = "hotel/{id}")
    public ResponseEntity<Hotel> update(@PathVariable Long id,@RequestBody Hotel hotel){
        System.out.println("PUT");
        Hotel existingHotel= hotelRepository.findById(id).orElse(null);
        if(existingHotel == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        existingHotel.setName(hotel.getName());
        existingHotel.setLocation(hotel.getLocation());
        Hotel savedHotel = hotelRepository.save(existingHotel);
        return new ResponseEntity(savedHotel, HttpStatus.NOT_FOUND);
    }





}
