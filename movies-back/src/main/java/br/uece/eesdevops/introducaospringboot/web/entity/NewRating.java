package br.uece.eesdevops.introducaospringboot.web.entity;

import br.uece.eesdevops.introducaospringboot.domain.entity.Movie;
import lombok.Data;

@Data 
public class NewRating {
	
	private Long id;
    private Movie movie;
    private Integer score;
    private String author;
    private String comment;

}
