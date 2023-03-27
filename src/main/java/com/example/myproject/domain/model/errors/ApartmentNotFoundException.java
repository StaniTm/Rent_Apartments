package com.example.myproject.domain.model.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApartmentNotFoundException extends RuntimeException {

    public ApartmentNotFoundException(String message) {
        super(message);
    }

    public HttpStatusCode getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
