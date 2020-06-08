package br.uece.eesdevops.cearamovies.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieRatingNotFoundException extends RuntimeException {

    public MovieRatingNotFoundException(Long id) {
        super("Book lending for ID " + id + " does not exist.");
    }

}
