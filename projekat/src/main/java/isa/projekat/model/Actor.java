package isa.projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Actor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id", nullable = false)
	private long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@ManyToMany
    @JoinTable(name = "movie_performance_actors",
    joinColumns = @JoinColumn(name = "actor_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "movie_performance_id", nullable = false))
	private Set<MoviePerformance> moviePerformance;
	
	public Actor() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<MoviePerformance> getMoviePerformance() {
		return moviePerformance;
	}

	public void setMoviePerformance(Set<MoviePerformance> moviePerformance) {
		this.moviePerformance = moviePerformance;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
