package br.uece.eesdevops.introducaospringboot.domain.service;

import br.uece.eesdevops.introducaospringboot.domain.entity.Rating;
import br.uece.eesdevops.introducaospringboot.domain.entity.Rating;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookAlreadyLentException;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookLendingNotFoundException;
import br.uece.eesdevops.introducaospringboot.repository.RatingRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangeMovieRatingService {

    private final RatingRepository repository;

    public ChangeMovieRatingService(RatingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Rating execute(Long bookLendingId, Rating newRating) {
        Optional<Rating> optional = repository.findById(bookLendingId);

        if (optional.isPresent()) {
            return changeStatus(optional.get(), newRating);
        } else {
            throw new BookLendingNotFoundException(bookLendingId);
        }
    }

    private Rating changeStatus(Rating bookLending, Rating newStatus) {
//        if (bookLending.getStatus().equals(newStatus)) {
//            return bookLending;
//        }

//        if (newStatus.equals(Rating.LENT)) {
//            checkIfHasBookLent(bookLending.getBook().getId());
//        }

//        bookLending.setStatus(newStatus);

        return repository.save(bookLending);
    }

//    private void checkIfHasBookLent(Integer bookId) {
//        Optional<Rating> optional = repository.findBookLendingByBookId(bookId).stream()
//                .filter(bookLending -> bookLending.getStatus().equals(Rating.LENT))
//                .findAny();
//
//        if (optional.isPresent()) {
//            throw new BookAlreadyLentException(bookId);
//        }
//    }

}
