package com.example.libraryStore.web;

import com.example.libraryStore.domain.model.errors.ApartmentNotFoundException;
import com.example.libraryStore.domain.model.errors.CityNotFoundException;
import com.example.libraryStore.domain.model.errors.OfferNotFoundException;
import com.example.libraryStore.domain.model.errors.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ApartmentNotFoundException.class)
    public ModelAndView handleApartmentExceptions(ApartmentNotFoundException e) {
        e.printStackTrace();
        return new ModelAndView("404").
                addObject("message", e.getMessage()).
                addObject("statusCode", e.getStatusCode().value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OfferNotFoundException.class)
    public ModelAndView handleOfferNotFoundException(OfferNotFoundException e) {
        e.printStackTrace();
        return new ModelAndView("404").
                addObject("message", e.getMessage()).
                addObject("statusCode", e.getStatusCode().value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityNotFoundException.class)
    public ModelAndView handleCityNotFoundException(CityNotFoundException e) {
        e.printStackTrace();
        return new ModelAndView("404").
                addObject("message", e.getMessage()).
                addObject("statusCode", e.getStatusCode().value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException(UserNotFoundException e) {
        e.printStackTrace();
        return new ModelAndView("404").
                addObject("message", e.getMessage()).
                addObject("statusCode", e.getStatusCode());
    }

}
