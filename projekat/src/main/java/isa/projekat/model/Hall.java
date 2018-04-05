package isa.projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Hall implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@ManyToOne(optional = false)
	private TheatreCinema theatreCinema;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
	private Set<Segment> segments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
	private Set<AmbientRating> ratings;
	
	public Hall() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TheatreCinema getTheatreCinema() {
		return theatreCinema;
	}

	public void setTheatreCinema(TheatreCinema theatreCinema) {
		this.theatreCinema = theatreCinema;
	}

	public Set<Segment> getSegments() {
		return segments;
	}

	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}
	
}
