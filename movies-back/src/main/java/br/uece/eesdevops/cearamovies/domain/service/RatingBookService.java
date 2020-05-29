package br.uece.eesdevops.cearamovies.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import br.uece.eesdevops.cearamovies.domain.entity.Rating;
import br.uece.eesdevops.cearamovies.domain.exception.InvalidBookLendingException;
import br.uece.eesdevops.cearamovies.domain.exception.MovieNotFoundException;
import br.uece.eesdevops.cearamovies.repository.MovieRepository;
import br.uece.eesdevops.cearamovies.repository.RatingRepository;

@Service
public class RatingBookService {

    private final MovieRepository movieRepository;
    private final RatingRepository movieLendingRepository;

    public RatingBookService(
            MovieRepository movieRepository,
            RatingRepository movieLendingRepository
    ) {
        this.movieRepository = movieRepository;
        this.movieLendingRepository = movieLendingRepository;
    }

    @Transactional
    public Rating execute(Rating rating) {
        requireBookAndStudent(rating);

         Movie movie = getBookOrThrow(rating.getMovie().getId());
//        Student student = getStudentOrThrow(rating.getStudent().getId());

//        checkIfBookIsAlreadyLent(rating.getMovie().getId());

        rating.setMovie(movie);
//        rating.setStudent(student);
//        rating.setStatus(BookLendingStatus.LENT);

        return movieLendingRepository.save(rating);
    }

    private void requireBookAndStudent(Rating movieLending) {
        if (movieLending.getMovie() == null || movieLending.getMovie().getId() == null) {
            throw new InvalidBookLendingException("Book ID is null");
        }

//        if (movieLending.getStudent() == null || movieLending.getStudent().getId() == null) {
//            throw new InvalidBookLendingException("Student ID is null");
//        }
    }

    private Movie getBookOrThrow(Long movieId) {
        Optional<Movie> optional = movieRepository.findById(movieId);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new MovieNotFoundException(movieId);
        }
    }


//    private void checkIfBookIsAlreadyLent(Integer movieId) {
//        Optional<Rating> optional = movieLendingRepository.findBookLendingByBookId(movieId).stream()
//                .filter(movieLending -> movieLending.getStatus().equals(BookLendingStatus.LENT))
//                .findAny();
//
//        if (optional.isPresent()) {
//            throw new BookAlreadyLentException(movieId);
//        }
//    }

}
