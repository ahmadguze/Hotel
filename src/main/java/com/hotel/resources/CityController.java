package com.hotel.resources;
import com.hotel.helper.ElementNotFound;
import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.moels.Location;
import com.hotel.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        System.out.println("GET");
        return (List<City>) cityRepository.findAll();
    }

    @PostMapping(value = "city")
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody City city){
          return cityRepository.save(city);
    }

    @GetMapping("city/{id}")
    public City getOne(@PathVariable Long id){
        return cityRepository.findById(id).orElseThrow(()->new ElementNotFound());
    }

    @PutMapping(value = "city/{id}")
    public ResponseEntity<City> update(@PathVariable Long id,@RequestBody City city){
        City existingCity= getOne(id);
        existingCity.setName(city.getName());
        existingCity.setLocation(city.getLocation());
        City savedCity = cityRepository.save(existingCity);
        return new ResponseEntity(savedCity, HttpStatus.NOT_FOUND);
    }

    @PatchMapping(value = "city/{id}")
    public ResponseEntity<City> PartiallyUpdate(@PathVariable Long id,@RequestBody City city){
        City existingCity= getOne(id);
        if(existingCity == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Location existingLocation = existingCity.getLocation();
        Location location = city.getLocation();
        if(city.getName() != null)
              existingCity.setName(city.getName());
        if(location  != null){
            if(location.getLatitude() != null)
                existingLocation.setLatitude(location.getLatitude());
            if(location.getLongitude() != null)
                existingLocation.setLongitude(location.getLongitude());
        }
        City savedCity = cityRepository.save(existingCity);
        return new ResponseEntity(savedCity, HttpStatus.OK);
    }


    @GetMapping("city/{id}/nearestThreeHotels")
    public List<Hotel> nearestThreeHotels(@PathVariable long id){
        City city = getOne(id);
        return city.ThreeNearest();
    }


}
