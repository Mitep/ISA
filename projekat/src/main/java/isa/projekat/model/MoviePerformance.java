package isa.projekat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MoviePerformance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_performance_id", nullable = false)
	private Long movieId;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TheatreCinemaEnum type;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="director_id")
	@JsonIgnore
	private Director director;
	
	@ManyToMany(mappedBy = "moviePerformance")
	@JsonIgnore
	private Set<Genre> genre;
	
	@ManyToMany(mappedBy = "moviePerformance")
	@JsonIgnore
	private Set<Actor> actors;
	
	//broj sekundi trajanja pa to posle pretvorimo u hh:mm:ss
	@Column(name = "length", nullable = false)
	private int length;
	
	//nisam siguran da li se cuva kao path do slike ili kao kodiran string
	@Column(name = "poster", nullable = false)
	private String poster;
	
	@ManyToMany(mappedBy = "moviePer")
	@JsonIgnore
	private List<Oglas> oglas;
	
	public MoviePerformance() {
		
	}

	
	public MoviePerformance(TheatreCinemaEnum type, String name, Director director, Set<Genre> genre, Set<Actor> actors,
			int length, String poster) {
		super();
		this.type = type;
		this.name = name;
		this.director = director;
		this.genre = genre;
		this.actors = actors;
		this.length = length;
		this.poster = poster;
		this.oglas = new ArrayList<Oglas>();
	}


	public List<Oglas> getOglas() {
		return oglas;
	}


	public void setOglas(List<Oglas> oglas) {
		this.oglas = oglas;
	}


	

	public Long getMovieId() {
		return movieId;
	}


	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}


	public TheatreCinemaEnum getType() {
		return type;
	}

	public void setType(TheatreCinemaEnum type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Set<Genre> getGenre() {
		return genre;
	}

	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	
	
}
