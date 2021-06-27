package com.hotel.service;
import com.hotel.helper.ElementNotFound;
import com.hotel.moels.City;
import com.hotel.moels.Hotel;
import com.hotel.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Handler;

public interface CityService {


    public City create (City city);

    public City getOne (Long id);

    public void delete(Long id);

    public List<City> getAll();

    public City update(City city, Long id);

    public City partiallyUpdate(City city, Long id);

    public List<Hotel> nearestThreeHotels(Long id);










}
