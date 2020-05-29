package br.uece.eesdevops.introducaospringboot.web;

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

import br.uece.eesdevops.introducaospringboot.domain.entity.Rating;
import br.uece.eesdevops.introducaospringboot.domain.exception.BookLendingNotFoundException;
import br.uece.eesdevops.introducaospringboot.domain.service.ChangeMovieRatingService;
import br.uece.eesdevops.introducaospringboot.domain.service.RatingBookService;
import br.uece.eesdevops.introducaospringboot.repository.RatingRepository;
import br.uece.eesdevops.introducaospringboot.web.entity.NewMovie;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingController {

	private final ModelMapper mapper = new ModelMapper();
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
	public ResponseEntity<List<Rating>> getAll() {
		List<Rating> lendings = repository.findAll();
		return ResponseEntity.ok(lendings);
	}

	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Rating> getById(@PathVariable Long id) {
		Optional<Rating> lending = repository.findById(id);
		if (!lending.isPresent()) {
			throw new BookLendingNotFoundException(id);
		} else {
			return ResponseEntity.ok(lending.get());
		}
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Rating> save(@RequestBody NewMovie request) {
		Rating saved = lendBookService.execute(mapper.map(request, Rating.class));
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PatchMapping(value = "/{id}/status", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Rating> patchStatus(@PathVariable Long id, @RequestBody NewMovie request) {
		Rating changed = changeBookLendingStatusService.execute(id, mapper.map(request, Rating.class));
		return ResponseEntity.ok(changed);
	}

}
