package isa.projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "director")
public class Director implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "director")
	private Set<MoviePerformance> moviePerformance;
	
	public Director() {
		
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

}
