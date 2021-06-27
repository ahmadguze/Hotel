package com.hotel.service;

import com.hotel.moels.Hotel;
import java.util.List;

public interface HotelService {


    public Hotel create (Hotel hotel);

    public Hotel getOne (Long id);

    public void delete(Long id);

    public List<Hotel> getAll();

    public Hotel update(Hotel hotel, Long id);

    public Hotel partiallyUpdate(Hotel hotel, Long id);



}
