package br.uece.eesdevops.cearamovies.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
- title,synopsis,producer,protagonist,year
 
 * @author marco
 *
 */
@Data
@Entity
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

    
    @Column(name = "year", nullable = false)
    private Integer year;
    

}
