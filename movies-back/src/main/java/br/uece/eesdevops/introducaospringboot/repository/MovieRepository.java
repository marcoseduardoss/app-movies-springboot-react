package br.uece.eesdevops.introducaospringboot.repository;

import br.uece.eesdevops.introducaospringboot.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> { }
