package br.uece.eesdevops.cearamovies.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.uece.eesdevops.cearamovies.web.entity.NewMovie;
import lombok.*;
/**
- title,synopsis,producer,protagonist,year
 
 * @author marco
 *
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "movie", schema = "public")
public class Movie {

    @Id
    @NonNull 
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

    
    @Column(name = "year", nullable = false)
    private Integer year;
    
    
    
    public NewMovie toDomain() {
    	return new ModelMapper().map(this, NewMovie.class);
    }


}
