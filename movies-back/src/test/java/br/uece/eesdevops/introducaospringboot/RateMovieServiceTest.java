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

@DisplayName("Runs all tests for domain service class responsible for lating a movie")
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
//
//    @Test
//    @DisplayName("should not rating a movie when movie is null")
//    void should_not_rate_a_movie_when_movie_or_student_is_null() {
//        assertThrows(
//                InvalidMovieRatingException.class,
//                () -> service.execute(fakeMovieRating(null, fakeStudent())),
//                "Movie ID is null."
//        );
//
//        Movie movieWithNoId = fakeMovie();
//        movieWithNoId.setId(null);
//
//        assertThrows(
//                InvalidMovieRatingException.class,
//                () -> service.execute(fakeMovieRating(movieWithNoId, fakeStudent())),
//                "Movie ID is null."
//        );
//
//        assertThrows(
//                InvalidMovieRatingException.class,
//                () -> service.execute(fakeMovieRating(fakeMovie(), null)),
//                "Student ID is null."
//        );
//
//        Student studentWithNoId = fakeStudent();
//        studentWithNoId.setId(null);
//
//        assertThrows(
//                InvalidMovieRatingException.class,
//                () -> service.execute(fakeMovieRating(fakeMovie(), studentWithNoId)),
//                "Student ID is null."
//        );
//
//        verifyNoInteractions(movieRepository);
//        verifyNoInteractions(studentRepository);
//        verifyNoInteractions(movieRatingRepository);
//        verifyNoInteractions(movieRatingRepository);
//    }
//
//    @Test
//    @DisplayName("should not rate a movie when movie does not exist")
//    void should_not_rate_a_movie_when_movie_does_not_exist() {
//        Movie movie = fakeMovie();
//
//        when(movieRepository.findById(movie.getId())).thenReturn(Optional.empty());
//
//        assertThrows(
//                MovieNotFoundException.class,
//                () -> service.execute(fakeMovieRating(movie, fakeStudent())),
//                "Movie for ID " + movie.getId() + " does not exist."
//        );
//    }
//
//    @Test
//    @DisplayName("should not rate a movie when student does not exist")
//    void should_not_rate_a_movie_when_student_does_not_exist() {
//        Movie movie = fakeMovie();
//        Student student = fakeStudent();
//
//        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
//        when(studentRepository.findById(student.getId())).thenReturn(Optional.empty());
//
//        assertThrows(
//                StudentNotFoundException.class,
//                () -> service.execute(fakeMovieRating(fakeMovie(), student)),
//                "Student for ID " + student.getId() + " does not exist."
//        );
//    }
//
//    @Test
//    @DisplayName("should not rate a movie when movie is already lent")
//    void should_not_rate_a_movie_when_movie_is_already_lent() {
//        Movie movie = fakeMovie();
//        Student student = fakeStudent();
//
//        when(movieRepository.findById(movie.getId()))
//                .thenReturn(Optional.of(movie));
//
//        when(studentRepository.findById(student.getId()))
//                .thenReturn(Optional.of(student));
//
//        when(movieRatingRepository.findMovieRatingByMovieId(movie.getId()))
//                .thenReturn(fakeMovieRatingList());
//
//        assertThrows(
//                MovieAlreadyLentException.class,
//                () -> service.execute(fakeMovieRating(fakeMovie(), student)),
//                "Movie for ID " + movie.getId() + " is already lent."
//        );
//    }
//    
}
