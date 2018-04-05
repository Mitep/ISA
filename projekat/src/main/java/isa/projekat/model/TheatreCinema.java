package isa.projekat.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "theatreCinema")
public class TheatreCinema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	//u zavisnosti od tipa znamo da li je bioskop ili pozoriste
	private TheatreCinemaEnum type;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String adress;
	
	@Column(nullable = false)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "theatreCinema")
	private Set<Hall> halls;
	
	private TheatreCinema() {
		
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
	
}
