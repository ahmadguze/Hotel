package com.hotel.repositories;

import com.hotel.moels.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

}
