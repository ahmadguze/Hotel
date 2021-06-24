package com.hotel.helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementNotFound extends RuntimeException{

    public ElementNotFound(){
       super("Not found");
    }

}
