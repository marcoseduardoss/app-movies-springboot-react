package br.uece.eesdevops.cearamovies.web;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.eesdevops.cearamovies.domain.entity.Rating;
import br.uece.eesdevops.cearamovies.domain.exception.BookLendingNotFoundException;
import br.uece.eesdevops.cearamovies.domain.service.ChangeMovieRatingService;
import br.uece.eesdevops.cearamovies.domain.service.RatingBookService;
import br.uece.eesdevops.cearamovies.repository.RatingRepository;
import br.uece.eesdevops.cearamovies.web.entity.NewMovie;
import br.uece.eesdevops.cearamovies.web.entity.NewRating;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingController {

	private final RatingRepository repository;
	private final RatingBookService lendBookService;
	private final ChangeMovieRatingService changeBookLendingStatusService;

	public RatingController(RatingRepository repository, RatingBookService lendBookService,
			ChangeMovieRatingService changeBookLendingStatusService) {
		
		this.repository = repository;
		
		this.lendBookService = lendBookService;
		
		this.changeBookLendingStatusService = changeBookLendingStatusService;
		
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
			throw new BookLendingNotFoundException(id);
	
		return ResponseEntity.ok(rating.get().toDomain());
		
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<NewRating> save(@RequestBody NewRating request) {
		
		Rating saved = lendBookService.execute(request.toDomain());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saved.toDomain());
		
	}

	@PatchMapping(value = "/{id}/status", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<NewRating> patchStatus(@PathVariable Long id, @RequestBody NewRating request) {
		
		Rating changed = changeBookLendingStatusService.execute(id, request.toDomain());

		return ResponseEntity.ok(changed.toDomain());
	}

}
