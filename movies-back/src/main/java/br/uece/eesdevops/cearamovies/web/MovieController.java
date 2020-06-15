package br.uece.eesdevops.cearamovies.web;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Optional<Movie> movie = repository.findById(id);

		if (!movie.isPresent())
			throw new MovieNotFoundException(id);
		
		repository.delete(movie.get());
		
		return ResponseEntity.noContent().build();
		
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
	
//	@PostMapping("/upload")
//	public ResponseEntity<?> multiUploadFileModel(@ModelAttribute FormWrapper model) {
//	    try {
//	        // Save as you want as per requiremens
//	        saveUploadedFile(model.getImage());
//	        formRepo.save(mode.getTitle(), model.getDescription());
//	    } catch (IOException e) {
//	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	    }
//
//	    return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);
//	}
	
	public class FormWrapper{
		private MultipartFile file;

		public MultipartFile getFile() {
			return file;
		}

		public void setFile(MultipartFile file) {
			this.file = file;
		}


		
		
	}
	

	@PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {

        try {
        	
        	// Get the file and save it somewhere
            return "data:image/png;base64, "+new String(Base64Utils.encode(file.getBytes()));
           

        } catch (IOException e) {
            e.printStackTrace();
            throw new MovieNotFoundException(e.getMessage());
        }

    }

}
