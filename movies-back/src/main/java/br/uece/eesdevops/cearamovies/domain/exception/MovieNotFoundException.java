package br.uece.eesdevops.cearamovies.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Long id) {
        super("Movie for ID " + id + " does not exist.");
    }
    public MovieNotFoundException(String msg) {
        super(msg);
    }
    
    
}
