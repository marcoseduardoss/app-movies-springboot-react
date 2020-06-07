package br.uece.eesdevops.cearamovies.web.entity;

import org.modelmapper.ModelMapper;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import br.uece.eesdevops.cearamovies.domain.entity.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class NewRating {
	
	private Long id;
    private Long idMovie;
    private Integer score;
    private String author;
    private String comment;
    
    
    /**
     * Create a mapper from NewRating to Rating
     * @return
     */
    public Rating toDomain() {
    	
    	Rating mapped = new ModelMapper().map(this, Rating.class);
    	
    	mapped.setMovie(new Movie(idMovie));
    	
    	return mapped;
	
    }

}
