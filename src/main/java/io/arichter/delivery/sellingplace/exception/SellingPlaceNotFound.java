package io.arichter.delivery.sellingplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SellingPlaceNotFound extends RuntimeException {

    public SellingPlaceNotFound() {
        super("Nenhum ponto de venda atenda a região informada");
    }
}
