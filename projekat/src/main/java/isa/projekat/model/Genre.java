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
public class Genre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_id", nullable = false)
	private long id;
	
	@Column(name = "genre_name", nullable = false)
	private String name;
	
	@ManyToMany
    @JoinTable(name = "movie_performance_genre",
    joinColumns = @JoinColumn(name = "genre_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "movie_performance_id", nullable = false))
	private Set<MoviePerformance> moviePerformance;
	
	public Genre() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<MoviePerformance> getMoviePerformance() {
		return moviePerformance;
	}

	public void setMoviePerformance(Set<MoviePerformance> moviePerformance) {
		this.moviePerformance = moviePerformance;
	}
	
}
