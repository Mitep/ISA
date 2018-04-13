package isa.projekat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MoviePerformanceRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rating_id", nullable = false)
	private long id;
	
	@Column(name = "rating", nullable = false)
	private int rating;
	
	@ManyToOne(optional = false)
	private MoviePerformance moviePerformance;
	
	@ManyToOne(optional = false)
	private User user;
	
	public MoviePerformanceRating() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public MoviePerformance getMoviePerformance() {
		return moviePerformance;
	}

	public void setMoviePerformance(MoviePerformance moviePerformance) {
		this.moviePerformance = moviePerformance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
