package com.hotel.resources;
import com.hotel.moels.City;
import com.hotel.moels.History;
import com.hotel.moels.Hotel;
import com.hotel.moels.Location;
import com.hotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class CityController {

    @Autowired
    CityService cityService;

    public static final String TOPIC = "History";

    @Autowired
    private KafkaTemplate<String, History> kafkaTemplate;

    @DeleteMapping("city/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        cityService.delete(id);
        kafkaTemplate.send(TOPIC, new History("delete", "delete", "city", id ) );
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("city")
    public List<City> showAll(){
        kafkaTemplate.send(TOPIC, new History("Get all", "get", "city", null ) );
        return cityService.getAll();
    }

    @PostMapping(value = "city")
    @ResponseStatus(HttpStatus.CREATED)
    public City create(@RequestBody City city){
        City cratedCity = cityService.create(city);
        kafkaTemplate.send(TOPIC, new History("Create", "Post", "city", cratedCity.getId()) );
        return cratedCity;
    }

    @GetMapping("city/{id}")
    public City getOne(@PathVariable Long id){
        kafkaTemplate.send(TOPIC, new History("Get one", "get", "city", id ) );
        return cityService.getOne(id);
    }

    @PutMapping(value = "city/{id}")
    public City update(@PathVariable Long id,@RequestBody City city){
        kafkaTemplate.send(TOPIC, new History("update", "Put", "city", id ) );
        return cityService.update(city,id);
    }

    @PatchMapping(value = "city/{id}")
    public City PartiallyUpdate(@PathVariable Long id,@RequestBody City city){
        kafkaTemplate.send(TOPIC, new History("Partially update", "Put", "city", id ) );
        return cityService.partiallyUpdate(city, id);
    }


    @GetMapping("city/{id}/nearestThreeHotels")
    public List<Hotel> nearestThreeHotels(@PathVariable long id){
        kafkaTemplate.send(TOPIC, new History("Three nearest hotels", "get", "city", id ) );
        return cityService.nearestThreeHotels(id);
    }


}
