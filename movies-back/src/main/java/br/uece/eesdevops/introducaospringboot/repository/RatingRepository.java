package br.uece.eesdevops.introducaospringboot.repository;

import br.uece.eesdevops.introducaospringboot.domain.entity.Rating;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findMovieLendingByMovieId(Long movieId);

}
 