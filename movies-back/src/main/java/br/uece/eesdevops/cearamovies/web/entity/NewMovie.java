package br.uece.eesdevops.cearamovies.web.entity;

import lombok.Data;

@Data
public class NewMovie {

	private Long id;
	private String title;
	private String synopsis;
	private String protagonists;
	private String producer;
	private String thumbnails;
	private Integer year;

}
