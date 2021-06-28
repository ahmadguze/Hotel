package com.hotel;

import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.repositories.CityRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class HotelApplicationTests {
    @Autowired
    JdbcTemplate  jdbc;


    @Autowired
    CityRepository cityRepository;
    @Test
    void contextLoads() {

    }

    @Test
    void testAll(){
        List<City> all= (List<City>) cityRepository.findAll();

        for(City city : all){
            System.out.println(city);
        }
    }
    @Transactional
    @Test
    void testThreeNearest(){
        City city = cityRepository.findById(1L).get();
        for(Hotel hotel : city.ThreeNearest()){
            System.out.println(hotel);
        }
    }


}