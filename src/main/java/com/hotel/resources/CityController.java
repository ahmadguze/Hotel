package com.hotel.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.repositories.CityRepository;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class CityController {

    @Autowired
    CityRepository cityRepository;

    @DeleteMapping("city/{id}")
    public ResponseEntity delete(@PathVariable Long id){
         cityRepository.deleteById(id);
         return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("city")
    public List<City> showAll(){
        return (List<City>) cityRepository.findAll();
    }

    @PostMapping(value = "city")
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody City city){
          return cityRepository.save(city);
    }

    @PutMapping(value = "city/{id}")
    public ResponseEntity<City> update(@PathVariable Long id,@RequestBody City city){
        City existingCity= cityRepository.findById(id).orElse(null);
        if(city == null)
            return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
        existingCity.setName(city.getName());
        existingCity.setLocation(city.getLocation());
        return new ResponseEntity<City>(cityRepository.save(existingCity),HttpStatus.OK);
    }


    @GetMapping("city/{id}/nearestThreeHotels")
    public List<Hotel> nearestThreeHotels(@PathVariable long id){
        City city = cityRepository.findById(id).get();
        return city.ThreeNearest();
    }


}
