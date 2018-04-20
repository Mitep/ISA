
package isa.projekat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "oglas")
public class Oglas implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@OneToOne(mappedBy = "oglas", cascade = CascadeType.ALL, 
            fetch = FetchType.EAGER, optional = false)
	private RequestOglasa requestOglasa;
	
	@ManyToOne(optional = true)
	@JsonIgnore
	private User vlasnik;
	
	@OneToMany(mappedBy = "ponudaOglas")
	@JsonIgnore
	private List<Offer> listaOffera;

	
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
		this.listaOffera = new ArrayList<Offer>();
	}
	


	public User getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(User vlasnik) {
		this.vlasnik = vlasnik;
	}

	public List<Offer> getListaOffera() {
		return listaOffera;
	}

	public void setListaOffera(List<Offer> listaOffera) {
		this.listaOffera = listaOffera;
	}

	public RequestOglasa getRequestOglasa() {
		return requestOglasa;
	}

	public void setRequestOglasa(RequestOglasa requestOglasa) {
		this.requestOglasa = requestOglasa;
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
