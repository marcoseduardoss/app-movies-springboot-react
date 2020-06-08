package br.uece.eesdevops.cearamovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.cearamovies.domain.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findMovieLendingByMovieId(Long movieId);

}
 