package isa.projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Hall implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hall_id", nullable = false)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="theatre_cinema_id")
	@JsonIgnore
	private TheatreCinema theatreCinema;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
	private Set<Segment> segments;
	
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

	public void setId(long id) {
		this.id = id;
	}
	
}
