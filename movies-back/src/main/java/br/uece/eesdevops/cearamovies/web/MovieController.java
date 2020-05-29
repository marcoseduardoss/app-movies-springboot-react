package br.uece.eesdevops.cearamovies.web;

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

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {

    private final MovieRepository repository;

    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movies = repository.findAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        Optional<Movie> movie = repository.findById(id);
        if (!movie.isPresent()) {
            throw new MovieNotFoundException(id);
        } else {
            return ResponseEntity.ok(movie.get());
        }
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        Movie saved = repository.save(movie);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @PutMapping(
            value = "/{id}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie movie) {
        
    	Optional<Movie> movieFromDb = repository.findById(id);
        
        if (!movieFromDb.isPresent())         
        	throw new MovieNotFoundException(id);
            
    	movie.setId(id);
        Movie updated = repository.save(movie);
        return ResponseEntity.ok(updated);
        
    }

}
