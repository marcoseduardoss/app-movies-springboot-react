package br.uece.eesdevops.cearamovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> { 
	
	List<Movie> findAllByOrderByIdAsc();
	
}
