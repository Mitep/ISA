package isa.projekat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TheatreCinema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theatre_cinema_id", nullable = false)
	private long tcId;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TheatreCinemaEnum type;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "adress", nullable = false)
	private String adress;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "theatreCinema")
	private Set<Hall> halls;
	
	@ManyToMany
    @JoinTable(name = "adminiObjekata",
    joinColumns = @JoinColumn(name = "objekat_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "user1_id", nullable = false))
	@JsonIgnore
	private List<User> adminiBioPoz;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "theatreCinema")
	@JsonIgnore
	private List<Projection> projekcije;
	
	
	@ManyToMany
	@JoinTable(name = "korisniciTC",
    joinColumns = @JoinColumn(name = "tc", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "korisnik", nullable = false))
	@JsonIgnore
	private List<User> listaKorisnika;
	
	
	private TheatreCinema() {
		
	}

	public TheatreCinema(String name, String adress, String description) {
		super();
		this.name = name;
		this.adress = adress;
		this.description = description;
		this.adminiBioPoz = new ArrayList<User>();
		this.projekcije = new ArrayList<Projection>();
		this.listaKorisnika = new ArrayList<User>();
	}
	
	
	

	public List<User> getListaKorisnika() {
		return listaKorisnika;
	}

	public void setListaKorisnika(List<User> listaKorisnika) {
		this.listaKorisnika = listaKorisnika;
	}

	public List<Projection> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(List<Projection> projekcije) {
		this.projekcije = projekcije;
	}

	public Long getId() {
		return tcId;
	}

	public void setId(Long id) {
		this.tcId = id;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Hall> getHalls() {
		return halls;
	}

	public void setHalls(Set<Hall> halls) {
		this.halls = halls;
	}


	public List<User> getAdminiBioPoz() {
		return adminiBioPoz;
	}

	public void setAdminiBioPoz(List<User> adminiBioPoz) {
		this.adminiBioPoz = adminiBioPoz;
	}
	
	
}
