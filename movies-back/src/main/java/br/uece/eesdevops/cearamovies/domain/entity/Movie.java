package br.uece.eesdevops.cearamovies.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.modelmapper.ModelMapper;

import br.uece.eesdevops.cearamovies.web.entity.NewMovie;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
- title,synopsis,producer,protagonist,year
 
 * @author marco
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "movie", schema = "public")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String synopsis;

    @Column(nullable = false)
    private String protagonists;
    
    @Column(nullable = false)
    private String producer;
    
    @Column(nullable = false)
    private String thumbnail;
    
    @Formula(value = "(select round(((select sum(r.score) from rating r where r.movie_id = id)::float/(select count(ra.id) from rating ra where ra.movie_id = id))::numeric))")
    private Integer score = 0;
    
    @Column(name = "year", nullable = false)
    private Integer year;
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "movie")
    private List<Rating> ratings = new ArrayList<>();
    

	public Movie(Long id) {
		this.id = id;
	}
    
    public NewMovie toDomain() {
    	return new ModelMapper().map(this, NewMovie.class);
    }


}
