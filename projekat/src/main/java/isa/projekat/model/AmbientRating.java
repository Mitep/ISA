package isa.projekat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ambientRating")
public class AmbientRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ambient_rating_id", nullable = false)
	private long id;
	
	@Column(name = "rating", nullable = false)
	private int rating;
	
	@ManyToOne(optional = false)
	private TheatreCinema theatreCinema;
	
	@ManyToOne(optional = false)
	private User user;
	
	public AmbientRating() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
}
