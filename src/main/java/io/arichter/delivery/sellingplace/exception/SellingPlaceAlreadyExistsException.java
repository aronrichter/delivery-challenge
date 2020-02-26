package io.arichter.delivery.sellingplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SellingPlaceAlreadyExistsException extends RuntimeException {

    public SellingPlaceAlreadyExistsException() {
        super("Já existe um ponto de venda para o documento informado");
    }
}
