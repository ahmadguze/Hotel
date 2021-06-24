package com.hotel.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.helper.LocationException;
import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.repositories.CityRepository;
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
        System.out.println("GET");
        return (List<City>) cityRepository.findAll();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LocationException.class)
    public String handleLocationException(){
          return "longitude must be between -180 and 180 and latitude between -90 and 90";
    }

    @PostMapping(value = "city")
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody City city){
          return cityRepository.save(city);
    }

    @GetMapping("city/{id}")
    public ResponseEntity<City> getOne(@PathVariable Long id){
        Optional<City> city = cityRepository.findById(id);
        if(!city.isPresent()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<City>(city.get(), HttpStatus.OK);
    }

    @PostMapping(value = "city/{id}")
    public ResponseEntity<City> update(@PathVariable Long id,@RequestBody City city){
        System.out.println("PUT");
        City existingCity= cityRepository.findById(id).orElse(null);
        if(existingCity == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        existingCity.setName(city.getName());
        existingCity.setLocation(city.getLocation());
        City savedCity = cityRepository.save(existingCity);
        return new ResponseEntity(savedCity, HttpStatus.NOT_FOUND);
    }


    @GetMapping("city/{id}/nearestThreeHotels")
    public List<Hotel> nearestThreeHotels(@PathVariable long id){
        City city = cityRepository.findById(id).get();
        return city.ThreeNearest();
    }


}
