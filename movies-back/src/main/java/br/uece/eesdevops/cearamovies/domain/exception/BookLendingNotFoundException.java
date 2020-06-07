package br.uece.eesdevops.cearamovies.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookLendingNotFoundException extends RuntimeException {

    public BookLendingNotFoundException(Long id) {
        super("Book lending for ID " + id + " does not exist.");
    }

}