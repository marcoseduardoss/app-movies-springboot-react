package br.uece.eesdevops.introducaospringboot;

import static br.uece.eesdevops.introducaospringboot.Fakes.fakeMovie;
import static br.uece.eesdevops.introducaospringboot.Fakes.fakeMovieRating;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import br.uece.eesdevops.cearamovies.domain.entity.Rating;
import br.uece.eesdevops.cearamovies.domain.service.MovieRatingService;
import br.uece.eesdevops.cearamovies.repository.MovieRepository;
import br.uece.eesdevops.cearamovies.repository.RatingRepository;

@DisplayName("Runs all tests for domain service class responsible for rating a movie")
class RateMovieServiceTest {

    private final MovieRepository movieRepository =
            mock(MovieRepository.class);

    private final RatingRepository movieRatingRepository =
            mock(RatingRepository.class);

    private MovieRatingService service;

    @BeforeEach
    void beforeEach() {
        service = new MovieRatingService(
                movieRepository,
                movieRatingRepository
        );
    }
    
    @Test
    @DisplayName("should rating a movie successfully")
    void should_rating_a_movie_successfully() {
        Movie movie = fakeMovie();
        Rating movieRating = fakeMovieRating(movie);

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        when(movieRatingRepository.save(movieRating)).thenReturn(movieRating);

        Rating saved = service.execute(movieRating);

        assertEquals(movie, saved.getMovie());

        verify(movieRepository).findById(movie.getId());
        verify(movieRatingRepository).save(movieRating);
    }
}
