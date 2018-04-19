package isa.projekat.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Projection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projection_id", nullable = false)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "projection_date_time", nullable = false)
	private long projectionDateTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection")
	@JsonIgnore
	private Set<Ticket> tickets;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="movie_performance_id")
	@JsonIgnore
	private MoviePerformance moviePerformance;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="hall_id")
	private Hall hall;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="theatre_cinema_id")
	@JsonIgnore
	private TheatreCinema theatreCinema;
	
	
	public Projection() {
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public MoviePerformance getMoviePerformance() {
		return moviePerformance;
	}

	public void setMoviePerformance(MoviePerformance moviePerformance) {
		this.moviePerformance = moviePerformance;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TheatreCinema getTheatreCinema() {
		return theatreCinema;
	}

	public void setTheatreCinema(TheatreCinema theatreCinema) {
		this.theatreCinema = theatreCinema;
	}

	public long getProjectionDateTime() {
		return projectionDateTime;
	}

	public void setProjectionDateTime(long projectionDateTime) {
		this.projectionDateTime = projectionDateTime;
	}
	
}
