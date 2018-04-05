package isa.projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "moviePerformance")
public class MoviePerformance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	//u zavisnosti od tipa znamo da li je film ili predstava
	private TheatreCinemaEnum type;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne(optional = false)
	private Director director;
	
	@ManyToMany
    @JoinTable(name="movieGenre")
	private Set<Genre> genre;
	
	@ManyToMany
    @JoinTable(name="actorsInMovies")
	private Set<Actor> actors;
	
	//broj sekundi trajanja pa to posle pretvorimo u hh:mm:ss
	@Column(nullable = false)
	private int length;
	
	//nisam siguran da li se cuva kao path do slike ili kao kodiran string
	@Column(nullable = false)
	private String poster;
	
	public MoviePerformance() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
