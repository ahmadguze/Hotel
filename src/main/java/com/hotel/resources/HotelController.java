package com.hotel.resources;

import com.hotel.moels.Hotel;
import com.hotel.moels.Hotel;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class HotelController {

    @Autowired
    HotelService hotelService;

    @DeleteMapping("hotel/{id}")
    public ResponseEntity delete(@PathVariable Long id){
         hotelService.delete(id);
         return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("hotel")
    public List<Hotel> showAll(){
        return hotelService.getAll();
    }

    @PostMapping(value = "hotel")
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel create(@RequestBody Hotel hotel){
         return hotelService.create(hotel);
    }

    @GetMapping("hotel/{id}")
    public Hotel getOne(@PathVariable Long id){
        return hotelService.getOne(id);
    }

    @PutMapping(value = "hotel/{id}")
    public Hotel update(@PathVariable Long id,@RequestBody Hotel hotel){
         return hotelService.update(hotel,id);
    }

    @PatchMapping(value = "hotel/{id}")
    public Hotel PartiallyUpdate(@PathVariable Long id,@RequestBody Hotel hotel){
       return hotelService.partiallyUpdate(hotel, id);
    }

}
