package isa.projekat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private long projId;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "projection_date_time", nullable = false)
	private long projectionDateTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection")
	@JsonIgnore
	private List<Ticket> tickets;
	
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
	
	
	@ManyToMany(mappedBy="listaProjekcija")
	private List<Seat> zauzetaSjedista;
	
	public Projection() {
		
		this.zauzetaSjedista = new ArrayList<Seat>();
	}
	
	

	public List<Seat> getZauzetaSjedista() {
		return zauzetaSjedista;
	}



	public void setZauzetaSjedista(List<Seat> zauzetaSjedista) {
		this.zauzetaSjedista = zauzetaSjedista;
	}

	private void addTicket(Ticket t){
		this.tickets.add(t);
	}
	
	public Long getId() {
		return projId;
	}

	public void setId(Long id) {
		this.projId = id;
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

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
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
