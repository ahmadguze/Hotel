package com.hotel.resources;
import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.moels.Location;
import com.hotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class CityController {

    @Autowired
    CityService cityService;

    @DeleteMapping("city/{id}")
    public ResponseEntity delete(@PathVariable Long id){
         cityService.delete(id);
         return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("city")
    public List<City> showAll(){
        return cityService.getAll();
    }

    @PostMapping(value = "city")
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody City city){
         return cityService.create(city);
    }

    @GetMapping("city/{id}")
    public City getOne(@PathVariable Long id){
        return cityService.getOne(id);
    }

    @PutMapping(value = "city/{id}")
    public City update(@PathVariable Long id,@RequestBody City city){
         return cityService.update(city,id);
    }

    @PatchMapping(value = "city/{id}")
    public City PartiallyUpdate(@PathVariable Long id,@RequestBody City city){
       return cityService.partiallyUpdate(city, id);
    }


    @GetMapping("city/{id}/nearestThreeHotels")
    public List<Hotel> nearestThreeHotels(@PathVariable long id){
          return cityService.nearestThreeHotels(id);
    }


}
