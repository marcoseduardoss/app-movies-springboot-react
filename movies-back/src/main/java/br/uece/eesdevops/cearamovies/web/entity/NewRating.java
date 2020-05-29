package br.uece.eesdevops.cearamovies.web.entity;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import lombok.Data;

@Data 
public class NewRating {
	
	private Long id;
    private Movie movie;
    private Integer score;
    private String author;
    private String comment;

}
