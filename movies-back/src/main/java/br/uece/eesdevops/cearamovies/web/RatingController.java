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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.cearamovies.domain.entity.Rating;
import br.uece.eesdevops.cearamovies.domain.exception.MovieRatingNotFoundException;
import br.uece.eesdevops.cearamovies.domain.service.MovieRatingService;
import br.uece.eesdevops.cearamovies.repository.RatingRepository;
import br.uece.eesdevops.cearamovies.web.entity.NewRating;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingController {

	private final RatingRepository repository;
	private final MovieRatingService movieRatingService;

	public RatingController(RatingRepository repository, MovieRatingService movieRatingService) {
		
		this.repository = repository;
		this.movieRatingService = movieRatingService;
		
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NewRating>> getAll() {
		
		List<Rating> ratings = repository.findAll();
		
		return ResponseEntity.ok(ratings.stream().map(rating -> {return rating.toDomain();}).collect(toList()));
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<NewRating> getById(@PathVariable Long id) {
		
		Optional<Rating> rating = repository.findById(id);
		
		if (!rating.isPresent())
			throw new MovieRatingNotFoundException(id);
	
		return ResponseEntity.ok(rating.get().toDomain());
		
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<NewRating> save(@RequestBody NewRating request) {
		
		Rating saved = movieRatingService.execute(request.toDomain());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saved.toDomain());
		
	}

}
