package br.uece.eesdevops.cearamovies.web;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import br.uece.eesdevops.cearamovies.domain.exception.MovieNotFoundException;
import br.uece.eesdevops.cearamovies.repository.MovieRepository;
import br.uece.eesdevops.cearamovies.web.entity.NewMovie;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {

	private final MovieRepository repository;

	
	public MovieController(MovieRepository repository) {
		
		this.repository = repository;
	
	}
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NewMovie>> getAll() {

		List<Movie> movies = repository.findAll();

		return ResponseEntity.ok(movies.stream().map(movie -> {return movie.toDomain();}).collect(toList()));
		
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<NewMovie> getById(@PathVariable Long id) {

		Optional<Movie> movie = repository.findById(id);

		if (!movie.isPresent())
			throw new MovieNotFoundException(id);

		return ResponseEntity.ok(movie.get().toDomain());
		
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<NewMovie> save(@RequestBody NewMovie newMovie) {

		Movie saved = repository.save(newMovie.toDomain());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saved.toDomain());
	}

	@PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<NewMovie> update(@PathVariable Long id, @RequestBody NewMovie movie) {

		if (!repository.findById(id).isPresent())
			throw new MovieNotFoundException(id);

		movie.setId(id);
		
		Movie updated = repository.save(movie.toDomain());
		
		return ResponseEntity.ok(updated.toDomain());

	}

}
