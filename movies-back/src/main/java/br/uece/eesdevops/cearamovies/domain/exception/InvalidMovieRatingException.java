package br.uece.eesdevops.cearamovies.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMovieRatingException extends RuntimeException {

    public InvalidMovieRatingException(String message) {
        super(message);
    }

}
