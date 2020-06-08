package br.uece.eesdevops.cearamovies.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import br.uece.eesdevops.cearamovies.domain.entity.Rating;
import br.uece.eesdevops.cearamovies.domain.exception.InvalidMovieRatingException;
import br.uece.eesdevops.cearamovies.domain.exception.MovieNotFoundException;
import br.uece.eesdevops.cearamovies.repository.MovieRepository;
import br.uece.eesdevops.cearamovies.repository.RatingRepository;

@Service
public class MovieRatingService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    public MovieRatingService(
            MovieRepository movieRepository,
            RatingRepository movieLendingRepository
    ) {
        this.movieRepository = movieRepository;
        this.ratingRepository = movieLendingRepository;
    }

    @Transactional
    public Rating execute(Rating rating) {
    	
        requireMovie(rating);

        Movie movie = getMovieOrThrow(rating.getMovie().getId());
        
		rating.setMovie(movie);
        
        return ratingRepository.save(rating);
    }

    private void requireMovie(Rating movieRating) {
    	
        boolean idValid = movieRating.getMovie() != null && movieRating.getMovie().getId() != null;
		
        if (!idValid)
            throw new InvalidMovieRatingException("Rating ID is null");
        
    }

    private Movie getMovieOrThrow(Long movieId) {
        
    	Optional<Movie> optional = movieRepository.findById(movieId);
        
        if (!optional.isPresent())
        	throw new MovieNotFoundException(movieId);
        
        return optional.get();
        
    }

}
