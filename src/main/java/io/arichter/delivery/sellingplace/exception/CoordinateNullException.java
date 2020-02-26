package io.arichter.delivery.sellingplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CoordinateNullException extends RuntimeException {

    public CoordinateNullException() {
        super("As coordenadas não podem ser nulas");
    }
}
