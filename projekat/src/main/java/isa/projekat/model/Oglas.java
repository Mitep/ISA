package isa.projekat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "oglas")
public class Oglas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long oglasId;
	
	@NotNull
	private String nazivOglasa;
	
	@NotNull
	private String opisOglasa;
	
	private String imageOglasa;
	
	@NotNull
	private String datumOglasa;

	@ManyToMany
	@JoinTable(name = "oglasiFilma",
	joinColumns = @JoinColumn(name = "oglas_id", nullable = false),
	inverseJoinColumns = @JoinColumn(name = "movie_id", nullable = false))
	@JsonIgnore
	private List<MoviePerformance> moviePer;
	

	
	public Oglas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Oglas(@NotNull String nazivOglasa, @NotNull String opisOglasa, String imageOglasa,
			@NotNull String datumOglasa) {
		super();
		this.nazivOglasa = nazivOglasa;
		this.opisOglasa = opisOglasa;
		this.imageOglasa = imageOglasa;
		this.datumOglasa = datumOglasa;
		this.moviePer = new ArrayList<MoviePerformance>();
	}
	

	public List<MoviePerformance> getMoviePer() {
		return moviePer;
	}

	public void setMoviePer(List<MoviePerformance> moviePer) {
		this.moviePer = moviePer;
	}

	public String getNazivOglasa() {
		return nazivOglasa;
	}

	public void setNazivOglasa(String nazivOglasa) {
		this.nazivOglasa = nazivOglasa;
	}

	public String getOpisOglasa() {
		return opisOglasa;
	}

	public void setOpisOglasa(String opisOglasa) {
		this.opisOglasa = opisOglasa;
	}

	public String getImageOglasa() {
		return imageOglasa;
	}

	public void setImageOglasa(String imageOglasa) {
		this.imageOglasa = imageOglasa;
	}

	public String getDatumOglasa() {
		return datumOglasa;
	}

	public void setDatumOglasa(String datumOglasa) {
		this.datumOglasa = datumOglasa;
	}

	public Long getOglasId() {
		return oglasId;
	}

	public void setOglasId(Long oglasId) {
		this.oglasId = oglasId;
	}
	
	
}
