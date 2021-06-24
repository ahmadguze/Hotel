package com.hotel.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.repositories.CityRepository;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping
public class CityController {

    @Autowired
    CityRepository cityRepository;
    @GetMapping("city")
    public List<City> showAll(){
        return (List<City>) cityRepository.findAll();
    }
    @GetMapping("city/{id}/nearestThreeHotels")
    public List<Hotel> nearestThreeHotels(@PathVariable long id){
        City city = cityRepository.findById(id).get();
        return city.ThreeNearest();
    }


}
