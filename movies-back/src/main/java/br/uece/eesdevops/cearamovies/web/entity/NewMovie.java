package br.uece.eesdevops.cearamovies.web.entity;

import org.modelmapper.ModelMapper;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import lombok.Data;

@Data
public class NewMovie {

	private Long id;
	private String title;
	private String synopsis;
	private String protagonists;
	private String producer;
	private String thumbnail;
	private Integer year;
	private Integer score = 0;
	
//	public NewMovie(Movie movie) {
//        this.id = movie.getId();
//        this.title = movie.getTitle();
//        this.synopsis = movie.getSynopsis();
//        this.protagonists = movie.getProtagonists();
//        this.producer = movie.getProducer();
//        this.thumbnails = movie.getThumbnail();
//        this.year = movie.getYear();
//    }
	
    public Movie toDomain() {
    	return new ModelMapper().map(this, Movie.class);
    }

}
