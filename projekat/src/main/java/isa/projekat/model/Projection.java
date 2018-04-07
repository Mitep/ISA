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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name = "projection_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date projectionDate;
	
	@Column(name = "projection_time", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date projectionTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection")
	private Set<ProjectionRating> ratings;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projection")
	private Set<Ticket> tickets;
	
	@ManyToOne(optional = false)
	private MoviePerformance moviePerformance;
	
	@ManyToOne(optional = false)
	private Hall hall;
	
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

	public Date getProjectionDate() {
		return projectionDate;
	}

	public void setProjectionDate(Date projectionDate) {
		this.projectionDate = projectionDate;
	}

	public Date getProjectionTime() {
		return projectionTime;
	}

	public void setProjectionTime(Date projectionTime) {
		this.projectionTime = projectionTime;
	}

	public Set<ProjectionRating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<ProjectionRating> ratings) {
		this.ratings = ratings;
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
	
}